package vn.seven.stc.umgr.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.seven.stc.umgr.models.Role;
import vn.seven.stc.umgr.services.RoleService;
import vn.seven.stc.config.ApplicationProperties;
import vn.seven.stc.core.CrudApiEndpoint;

/**
 * Created by quocvi3t on 11/14/17.
 */
@RestController
@RequestMapping("/api/roles")
@EnableConfigurationProperties(ApplicationProperties.class)
public class RoleApiEndpoint extends CrudApiEndpoint<Role,Long> {

    private static Logger logger = LoggerFactory.getLogger(RoleApiEndpoint.class);
    private RoleService roleService;

    @Autowired
    public RoleApiEndpoint(RoleService service) {
        super(service);
        this.roleService = service;
        this.baseUrl = "/api/roles";
    }
}