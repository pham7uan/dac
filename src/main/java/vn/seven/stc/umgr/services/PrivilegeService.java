package vn.seven.stc.umgr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.umgr.models.Privilege;
import vn.seven.stc.umgr.repositories.PrivilegeRepository;

import javax.transaction.Transactional;

/**
 * Created by quocvi3t on 11/16/17.
 */
@Service
@Transactional
public class PrivilegeService extends CrudService<Privilege,Long> {
    private  PrivilegeRepository privilegeRepository;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public PrivilegeService(PrivilegeRepository repository) {
        this.repository = this.privilegeRepository = repository;
    }

    public Privilege findOneByName(String name) {
        return this.privilegeRepository.findFirstByName(name);
    }
}
