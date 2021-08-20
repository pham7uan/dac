package vn.seven.stc.report.endpoints;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.*;
import vn.seven.stc.config.ApplicationProperties;
import vn.seven.stc.core.CrudApiEndpoint;
import vn.seven.stc.core.GeneralEntity;
import vn.seven.stc.masterdata.models.Device;
import vn.seven.stc.report.DeviceReport;
import vn.seven.stc.report.SearchReportInfo;
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

    @RequestMapping(value="/active-device", method=RequestMethod.POST)
    public DeviceReport getActiveDevice(@RequestBody SearchReportInfo searchReportInfo){
        return reportService.getDeviceReport(searchReportInfo);
    }

    @RequestMapping(value = "/export-active", method = RequestMethod.POST)
    public GeneralEntity exportActiveDevice(@RequestBody SearchReportInfo searchReportInfo){
        return reportService.exportActiveDevice(searchReportInfo);
    }


}