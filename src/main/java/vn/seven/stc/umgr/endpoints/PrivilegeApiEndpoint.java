package vn.seven.stc.umgr.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.seven.stc.umgr.models.Privilege;
import vn.seven.stc.umgr.services.PrivilegeService;
import vn.seven.stc.config.ApplicationProperties;
import vn.seven.stc.core.CrudApiEndpoint;

/**
 * Created by quocvi3t on 11/14/17.
 */
@RestController
@RequestMapping("/api/privileges")
@EnableConfigurationProperties(ApplicationProperties.class)
public class PrivilegeApiEndpoint extends CrudApiEndpoint<Privilege,Long> {

    private static Logger logger = LoggerFactory.getLogger(PrivilegeApiEndpoint.class);
    private PrivilegeService privilegeService;

    @Autowired
    public PrivilegeApiEndpoint(PrivilegeService service) {
        super(service);
        this.privilegeService = service;
        this.baseUrl = "/api/privileges";
    }
}
