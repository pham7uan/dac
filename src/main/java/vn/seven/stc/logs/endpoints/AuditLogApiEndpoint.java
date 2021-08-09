package vn.seven.stc.logs.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.seven.stc.logs.models.AuditLog;
import vn.seven.stc.logs.services.AuditLogService;
import vn.seven.stc.config.ApplicationProperties;
import vn.seven.stc.core.CrudApiEndpoint;

import java.util.List;

@RestController
@RequestMapping("/api/audit-logs")
@EnableConfigurationProperties(ApplicationProperties.class)
public class AuditLogApiEndpoint extends CrudApiEndpoint<AuditLog,Long> {

    private static Logger logger = LoggerFactory.getLogger(AuditLogApiEndpoint.class);
    private AuditLogService auditLogService;

    @Autowired
    public AuditLogApiEndpoint(AuditLogService auditLogService) {
        super(auditLogService);
        this.auditLogService = auditLogService;
        this.baseUrl = "/api/audit-logs";
    }
    @RequestMapping(path = "/get-by-object_type", method = RequestMethod.GET)
    public List<AuditLog> getAllByObjectType(@RequestParam("objectType") String objectType) {
        return auditLogService.findAllByObjectType(objectType);
    }
}
