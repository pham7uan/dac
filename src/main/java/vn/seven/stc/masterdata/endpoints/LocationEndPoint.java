package vn.seven.stc.masterdata.endpoints;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.seven.stc.core.CrudApiEndpoint;
import vn.seven.stc.masterdata.models.Device;
import vn.seven.stc.masterdata.models.Location;
import vn.seven.stc.masterdata.service.LocationService;

/**
 * Created by hiepnd
 * Date: 09/08/2021
 * Time: 3:01 PM
 * Contact me via mail hiepnd@vnpt-technology.vn
 */
@RestController
@RequestMapping("/api/location")
public class LocationEndPoint extends CrudApiEndpoint<Location, Long> {
    private LocationService locationService;

    public LocationEndPoint(LocationService service){
        super(service);
        this.locationService = service;
        this.baseUrl= "/api/location";
    }
}
