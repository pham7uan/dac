package vn.seven.stc.report.endpoints;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.seven.stc.config.ApplicationProperties;
import vn.seven.stc.core.CrudApiEndpoint;
import vn.seven.stc.masterdata.models.Device;
import vn.seven.stc.report.services.ReportService;

@RestController
@RequestMapping("/api/report")
@EnableConfigurationProperties(ApplicationProperties.class)
public class ReportEndpoint extends CrudApiEndpoint<Device, Long> {
    private ReportService reportService;

    public ReportEndpoint(ReportService service){
        super(service);
        this.reportService = service;
        this.baseUrl = "/api/report";
    }

}