package vn.seven.stc.masterdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.masterdata.models.Location;
import vn.seven.stc.masterdata.repositories.LocationRepository;
import vn.seven.stc.umgr.utils.SecurityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hiepnd
 * Date: 09/08/2021
 * Time: 2:55 PM
 * Contact me via mail hiepnd@vnpt-technology.vn
 */
@Service
public class LocationService  {
    private LocationRepository locationRepository;

    @Autowired
    public void setLocationRepository(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public void create(Map<String, Location> locationMap){
        List<Location> locations = new ArrayList<>();
        Location location;
        for (Map.Entry<String, Location> item: locationMap.entrySet()){
            if (!locationRepository.existsByLocationCode(item.getValue().getCode())){
                location = new Location();
                location.setCode(item.getValue().getCode());
                location.setName(item.getValue().getName());
                location.setAreaId(item.getValue().getAreaId());
                location.setCreated(System.currentTimeMillis());
                if(location.getCreatedBy() == null) {
                    String currentUsername = SecurityUtils.getCurrentUserLogin();
                    location.setCreatedBy(currentUsername);
                }
                locations.add(location);
            }
        }
        locationRepository.save(locations);

    }
}
