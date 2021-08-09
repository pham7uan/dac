package vn.seven.stc.logs.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.seven.stc.logs.services.LogService;


@RestController
@RequestMapping("/api/logs")
public class LogApiEndpoint {
    private LogService logService;

    @Autowired
    public void setLogService(LogService logService) {
        this.logService = logService;
    }

}
