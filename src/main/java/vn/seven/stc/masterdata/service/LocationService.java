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
public class LocationService  extends CrudService<Location, Long>{
    private LocationRepository locationRepository;

    public LocationService(LocationRepository repository){
        this.locationRepository = repository;
    }

    public void create(Map<String, Location> locationMap){

        for (Map.Entry<String, Location> item: locationMap.entrySet()){
            if (!locationRepository.existsByLocationCode(item.getValue().getCode())){
               locationRepository.save(item.getValue());
                beforeCreate(item.getValue());
            }
        }

    }
}
