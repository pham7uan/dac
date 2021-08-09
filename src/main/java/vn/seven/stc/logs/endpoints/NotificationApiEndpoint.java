package vn.seven.stc.logs.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.*;
import vn.seven.stc.logs.models.Note;
import vn.seven.stc.logs.services.NotificationService;
import vn.seven.stc.config.ApplicationProperties;
import vn.seven.stc.core.CrudApiEndpoint;

@RestController
@RequestMapping("/api/notifications")
@EnableConfigurationProperties(ApplicationProperties.class)
public class NotificationApiEndpoint extends CrudApiEndpoint<Note,Long> {

    private static Logger logger = LoggerFactory.getLogger(NotificationApiEndpoint.class);
    private NotificationService notificationService;

    @Autowired
    public NotificationApiEndpoint(NotificationService notificationService) {
        super(notificationService);
        this.notificationService = notificationService;
        this.baseUrl = "/api/notifications";
    }

    @RequestMapping(method = RequestMethod.GET,path = "/done")
    public void done(@RequestParam ("id") Long id, @RequestParam ("done") Boolean done) {
        notificationService.done(id,done);
    }



}
