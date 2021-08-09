package vn.seven.stc.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.seven.stc.umgr.utils.SecurityUtils;
import vn.seven.stc.core.utils.PaginationUtil;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by quocvi3t on 11/14/17.
 */
public abstract class CrudApiEndpoint<T extends AbstractEntity, ID extends Serializable> {

    private static Logger logger = LoggerFactory.getLogger(CrudApiEndpoint.class);

    protected CrudService<T,ID> service;

    protected String baseUrl;

    public CrudApiEndpoint(CrudService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<T>> list(Pageable pageable) {
        Page<T> page = service.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,baseUrl);
        return new ResponseEntity<>(page.getContent(),headers, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public T create(@RequestBody T entity) {
        logger.info("Call Create API by {}", SecurityUtils.getCurrentUserLogin());
        return service.create(entity);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public T update(@PathVariable(value = "id") ID id, @RequestBody T entity) {
        logger.info("Call Update API by {}",SecurityUtils.getCurrentUserLogin());
        return service.update(id,entity);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") ID id) {
        logger.info("Call delete API by {}",SecurityUtils.getCurrentUserLogin());
        service.deleteById(id);
        logger.info("Finish delete API by {}",SecurityUtils.getCurrentUserLogin());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public T get(@PathVariable(value = "id") ID id) {
        return service.get(id);
    }

    @RequestMapping(path="/search", method = RequestMethod.GET)
    public ResponseEntity<List<T>>  get(@RequestParam("query") String query, @PageableDefault(size = 20) Pageable pageable) {
        try{
            Page<T> page = service.search(query, pageable);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,baseUrl);
            return new ResponseEntity<>(page.getContent(),headers, HttpStatus.OK);
        } catch (Exception e){
            return null;
        }
    }

    @RequestMapping(path="/advanced-group", method = RequestMethod.GET)
    public ResponseEntity<List<T>>  advancedGroup(@RequestParam("query") String query,@PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        try{
            PageRequest p = new PageRequest(0, 1000000);
            Page<T> page = service.search(query, p);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,baseUrl);
            Integer offset = pageable.getPageNumber()*pageable.getPageSize() + pageable.getPageSize();
            if(offset > page.getContent().size()){
                offset = page.getContent().size();
            }
            List<T> content = page.getContent().subList(pageable.getPageNumber()*pageable.getPageSize(), offset);
            return new ResponseEntity<>(content,headers, HttpStatus.OK);
        } catch (Exception e){
            return null;
        }
    }

    @RequestMapping(path="/batch-delete", method = RequestMethod.POST)
    public Set<ID> batch_delete(@RequestBody Set<ID> listIDs) {
        logger.info("Call batch delete API by {}",SecurityUtils.getCurrentUserLogin());
        Set<ID> fail = new HashSet<>();
        for(ID id : listIDs) {
            try {
                service.deleteById(id);
            } catch (Exception e) {
                logger.debug("Cannot delete object with id #{}, baseUrl: {}",id,this.baseUrl);
                fail.add(id);
            }
        }
        return fail;
    }

    @RequestMapping(path="/activate", method = RequestMethod.GET)
    public void activate(@RequestParam("id") ID id) {
        service.activate(id);
    }

    @RequestMapping(path="/deactivate", method = RequestMethod.GET)
    public void deactivate(@RequestParam("id") ID id) {
        service.deactivate(id);
    }
}
