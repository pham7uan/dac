package vn.seven.stc.core;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.RSQLParserException;
import cz.jirutka.rsql.parser.ast.Node;
import org.javers.core.JaversBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import javax.transaction.Transactional;
import org.springframework.util.StringUtils;
import vn.seven.stc.core.errors.BadRequestAlertException;
import vn.seven.stc.logs.converters.CustomValueChange;
import vn.seven.stc.umgr.utils.SecurityUtils;
import vn.seven.stc.core.errors.UnauthorizedExpcetion;
import vn.seven.stc.core.rsql.CustomRsqlVisitor;
import org.javers.core.Javers;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ValueChange;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.*;

/**
 * Created by quocvi3t on 11/14/17.
 */
@Transactional
public class CrudService<T extends AbstractEntity, ID extends Serializable> {
    private static Logger logger = LoggerFactory.getLogger(CrudService.class);
    protected CustomJpaRepository<T,ID> repository;

    protected CacheService cacheService;

    @Autowired
    public void setCacheService(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    public T get(ID id) {
        SecurityUtils.validatePermission(T.getPrivView());
        return repository.findOne(id);
    }

    public T simpleGet(ID id) {
        return repository.findOne(id);
    }

    public List<T> findAll() {
        SecurityUtils.validatePermission(T.getPrivView());
        return repository.findAll();
    }

    public Page<T> findAll(Pageable pageable) {
        SecurityUtils.validatePermission(T.getPrivView());
        return repository.findAll(pageable);
    }

    public List<T> search(String query) {
        SecurityUtils.validatePermission(T.getPrivView());
        if(StringUtils.isEmpty(query)){
            return repository.findAll();
        }
        Node rootNode = new RSQLParser().parse(query);
        Specification<T> spec = rootNode.accept(new CustomRsqlVisitor<T>());
        return repository.findAll(spec);
    }

    public Page<T> search(String query, Pageable pageable) {
        return searchByQuery(query, pageable);
    }

    public Page<T> searchByQuery(String query, Pageable pageable){
        logger.info("query:{}",query);
        if(StringUtils.isEmpty(query)){
            return repository.findAll(pageable);
        }
        try {
            Node rootNode = new RSQLParser().parse(query);
            Specification<T> spec = rootNode.accept(new CustomRsqlVisitor<T>());
            return repository.findAll(spec, pageable);
        } catch(RSQLParserException pe) {
            pe.printStackTrace();
            logger.error("SEARCH FAIL: {}",query);
            return emptyPage();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("SEARCH FAIL: {}",query);
            return emptyPage();
        }
    }

    public T create(T entity) {
//        SecurityUtils.validatePermission(T.getPrivCreate());
        beforeCreate(entity);
        repository.save(entity);
        afterCreate(entity);
        return entity;
    }

    public List<T> create(List<T> entities) {
        if(entities !=null && entities.size() > 0){
            for(T t: entities){
                beforeCreate(t);
            }
            repository.save(entities);
            for(T t: entities){
                afterCreate(t);
            }
        }

        return entities;
    }

    public T update(ID id, T entity) {
        beforeUpdate(entity);
        T old = get(id);
        if(entity.getCreated() == null) entity.setCreated(old.getCreated());
        if(entity.getCreatedBy() == null) entity.setCreatedBy(old.getCreatedBy());
        if(old == null) {
            throw new EntityNotFoundException("No entity with id " + id);

        }
        repository.save(entity);
        afterUpdate(old,entity);
        return entity;
    }

    public List<T> saveMany(List<T> entities){
        SecurityUtils.validatePermission(T.getPrivUpdate());
        for (T entity : entities){
            beforeCreate(entity);
        }
        repository.save(entities);
        return entities;
    }

    public List<T> updateMany(List<T> entities){
        SecurityUtils.validatePermission(T.getPrivUpdate());
        for (T entity : entities){
            beforeUpdate(entity);
        }
        repository.save(entities);
        return entities;
    }

    public void delete(T entity) {
        SecurityUtils.validatePermission(T.getPrivDelete());
        if(entity.getCreatedBy().equals("system")){
            throw new EntityNotFoundException("Can not remove system object");
        }
        beforeDelete(entity);
        repository.delete(entity);
        afterDelete(entity);
    }

    public void deleteById(ID id) {
        SecurityUtils.validatePermission(T.getPrivDelete());
        T entity = get(id);
        if(entity.getCreatedBy().equals("system")){
            throw new EntityNotFoundException("Can not remove system object");
        }
        delete(entity);
    }

    public Long count() {
        return repository.count();
    }

    public void batchDelete(List<ID> ids) {
        for(ID id : ids) {
            deleteById(id);
        }
    }

    protected void beforeCreate(T entity) {
        if(entity.getTenantId() == null){
            throw new BadRequestAlertException("Non tenant", "userAndPermission", "nonTenant");
        }
        entity.setCreated(System.currentTimeMillis());
        entity.setUpdated(System.currentTimeMillis());
        if(entity.getCreatedBy() == null) {
            String currentUsername = SecurityUtils.getCurrentUserLogin();
            entity.setCreatedBy(currentUsername);
        }
        if(entity.getActive() == null) {
            entity.setActive(true);
        }

    }

    protected void afterCreate(T entity) {

    }

    protected void beforeUpdate(T entity) {
        entity.setUpdated(System.currentTimeMillis());
        entity.setUpdatedBy(SecurityUtils.getCurrentUserLogin());
        if(entity.getActive() == null) {
            entity.setActive(true);
        }
    }

    protected void afterUpdate(T old, T updated) {

    }

    protected void beforeDelete(T entity) {
        if("system".equals(entity.getCreatedBy())) {
            //can not delete a system object
            throw new UnauthorizedExpcetion("canNotDeleteSystemObject");
        }
    }

    protected void afterDelete(T entity) {

    }

    public Page<T> emptyPage() {
        return new Page<T>() {
            @Override
            public int getTotalPages() {
                return 0;
            }

            @Override
            public long getTotalElements() {
                return 0;
            }

            @Override
            public <S> Page<S> map(Converter<? super T, ? extends S> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public int getNumberOfElements() {
                return 0;
            }

            @Override
            public List<T> getContent() {
                return null;
            }

            @Override
            public boolean hasContent() {
                return false;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator<T> iterator() {
                return null;
            }
        };
    }

    public void activate(ID id) {
        SecurityUtils.validatePermission(T.getPrivUpdate());
        T t = repository.findOne(id);
        t.setActive(true);
        update(id, t);
    }

    public void deactivate(ID id) {
        SecurityUtils.validatePermission(T.getPrivUpdate());
        T t = repository.findOne(id);
        t.setActive(false);
        update(id, t);
    }

    public List<CustomValueChange> getChanges (T old, T updated){
        Javers javers = JaversBuilder.javers().build();
        Diff diff = javers.compare(old,updated);
        List<ValueChange> javersChanges = diff.getChangesByType(ValueChange.class);
        if(javersChanges.size() > 0) {
            List<CustomValueChange> changes = new ArrayList<>();
            for(ValueChange javersChange : javersChanges) {
                CustomValueChange change = new CustomValueChange();
                change.propertyName = javersChange.getPropertyName();
                change.oldValue = javersChange.getLeft();
                change.newValue = javersChange.getRight();
                changes.add(change);
                return changes;
            }
        }
        return null;
    }


}
