package vn.seven.stc.masterdata.service;

import org.springframework.stereotype.Service;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.masterdata.models.Location;
import vn.seven.stc.masterdata.repositories.LocationRepository;

/**
 * Created by hiepnd
 * Date: 09/08/2021
 * Time: 2:55 PM
 * Contact me via mail hiepnd@vnpt-technology.vn
 */
@Service
public class LocationService extends CrudService<Location, Long> {
    private LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.repository = this.locationRepository = locationRepository;
    }
}
