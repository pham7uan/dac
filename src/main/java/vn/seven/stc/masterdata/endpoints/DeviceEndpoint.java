package vn.seven.stc.masterdata.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.*;
import vn.seven.stc.config.ApplicationProperties;
import vn.seven.stc.core.CrudApiEndpoint;
import vn.seven.stc.masterdata.models.Device;
import vn.seven.stc.masterdata.service.DeviceService;
import vn.seven.stc.umgr.utils.SecurityUtils;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
@EnableConfigurationProperties(ApplicationProperties.class)
public class DeviceEndpoint extends CrudApiEndpoint<Device, Long> {
    private DeviceService deviceService;
    private static Logger logger = LoggerFactory.getLogger(DeviceService.class);
    public DeviceEndpoint(DeviceService service){
        super(service);
        this.deviceService = service;
        this.baseUrl = "/api/devices";
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public Device create(@RequestBody Device entity) {
        logger.info("Call Create API by {}", SecurityUtils.getCurrentUserLogin());
        return entity;      // không cho phép crud thủ công
    }
    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Device update(@PathVariable(value = "id") Long id, @RequestBody Device entity) {
        logger.info("Call Update API by {}",SecurityUtils.getCurrentUserLogin());
        return entity;  // không cho phép crud thủ công
    }
    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") Long id) {
        // // không cho phép crud thủ công
    }

    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    public void updateInformation(@RequestBody List<Device> devices) {
        deviceService.updateInformation(devices);
    }
}
