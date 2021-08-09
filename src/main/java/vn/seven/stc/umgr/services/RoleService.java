package vn.seven.stc.umgr.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.seven.stc.core.Constants;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.core.errors.BadRequestAlertException;
import vn.seven.stc.core.errors.RoleAlreadyUsedException;
import vn.seven.stc.core.errors.RoleHasNoPrivilegeException;
import vn.seven.stc.core.errors.UnauthorizedExpcetion;
import vn.seven.stc.umgr.models.Role;
import vn.seven.stc.umgr.repositories.RoleRepository;
import vn.seven.stc.umgr.utils.SecurityUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by quocvi3t on 11/16/17.
 */
@Service
@Transactional
public class RoleService extends CrudService<Role,Long> {
    private static Logger logger = LoggerFactory.getLogger(RoleService.class);
    @PersistenceContext
    private EntityManager entityManager;

    RoleRepository roleRepository;
    public RoleService(RoleRepository repository) {
        this.repository = this.roleRepository = repository;
    }

    @Override
    public Role get(Long id) {
        return this.roleRepository.findOneWithPrivilegesById(id);
    }

    @Override
    public Page<Role> findAll(Pageable pageable){
        if(SecurityUtils.isCurrentUserInRole(Constants.ROLE_SYSTEM_ADMIN)){
            return roleRepository.findAll(pageable); // duoc tim thay tat ca cac role
        } else {
            Set<String> defaultRoles = new HashSet<>();
            defaultRoles.add(Constants.ROLE_SYSTEM_ADMIN); // khong duoc tim thay role system admin
            return roleRepository.findAllByNameNotIn(defaultRoles,pageable);
        }
    }

    @Override
    public Page<Role> search(String query, Pageable pageable) {
        if(!SecurityUtils.isCurrentUserInRole(Constants.ROLE_SYSTEM_ADMIN)){
            if(query.isEmpty()){
                query =  "name!="+ Constants.ROLE_SYSTEM_ADMIN;
            } else {
                query +=  ";name!="+ Constants.ROLE_SYSTEM_ADMIN;
            }
        }
        return searchByQuery(query, pageable);
    }

    @Override
    protected void beforeCreate(Role entity) {
        entity.setTenantId(SecurityUtils.getTenantId());
        super.beforeCreate(entity);
        entity.setDisplayName(entity.getName());
        if(entity.getType() !=null && entity.getType()){
            throw new BadRequestAlertException("Cannot delete default role","role","defaultRole");
        }

        if(entity != null && entity.getPrivileges() != null && entity.getPrivileges().isEmpty())
            throw new RoleHasNoPrivilegeException();

        if(entity != null) {
            Role exist = this.roleRepository.findFirstByName(entity.getName());
            if(exist != null)
                throw new RoleAlreadyUsedException();
        }
    }


    @Override
    protected void beforeUpdate(Role entity) {
        super.beforeUpdate(entity);

        entity.setDisplayName(entity.getName());

        if(entity.getType()) {
            //can not delete a system object
            throw new UnauthorizedExpcetion("canNotEditSystemObject");
        }

        Role role = this.roleRepository.findFirstByName(entity.getName());
        if(role != null && !role.getId().equals(entity.getId())) {
            throw new RoleAlreadyUsedException();
        }
    }

    @Override
    protected void beforeDelete(Role entity) {
        super.beforeDelete(entity);

        if(entity.getType()) {
            //can not delete a system object
            throw new UnauthorizedExpcetion("canNotDeleteSystemObject");
        }

        if(entity.getPrivileges() != null && !entity.getPrivileges().isEmpty()){
            deletePrivilegeRelationByRoleId(entity.getId());
        }
    }

    public void deletePrivilegeRelationByRoleId(Long roleId){
        String query = "DELETE FROM base_role_privilege where role_id = " + roleId;
        Query q = entityManager.createNativeQuery(query);
        q.executeUpdate();
    }

}
