package vn.seven.stc.masterdata.endpoints;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.seven.stc.config.ApplicationProperties;
import vn.seven.stc.core.ApiCode;
import vn.seven.stc.core.ApiResponse;
import vn.seven.stc.core.CrudApiEndpoint;
import vn.seven.stc.core.utils.PaginationUtil;
import vn.seven.stc.masterdata.models.Device;
import vn.seven.stc.masterdata.models.DevicePubic;
import vn.seven.stc.masterdata.service.DeviceService;
import vn.seven.stc.umgr.utils.SecurityUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/devices")
@EnableConfigurationProperties(ApplicationProperties.class)
public class DeviceEndpoint extends CrudApiEndpoint<Device, Long> {
    private DeviceService deviceService;
    private static Logger logger = LoggerFactory.getLogger(DeviceEndpoint.class);
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

    @RequestMapping(path = "/sync", method = RequestMethod.POST)
    public ApiResponse updateInformation(@RequestBody List<DevicePubic> devicePubic) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse = deviceService.update(devicePubic,apiResponse);
        } catch (Exception e){
            e.printStackTrace();
            apiResponse.setCode(ApiCode.FAIL);
            apiResponse.setMessage(ApiCode.MSG_500);
        }
        return apiResponse;
    }

    @RequestMapping(path = "/export/{column}", method = RequestMethod.GET)
    public Map<String,String> exportTransfer(@RequestParam String param, @PathVariable String column) throws IOException {
        Page<Device> page = deviceService.search(param, createPageRequest());
        return deviceService.exportDevice(page.getContent(), column);
    }

    private Pageable createPageRequest() {
        Sort sort = new Sort(Sort.Direction.ASC,"updated");
        return new PageRequest(0, 1000000,sort);
    }
}
