package vn.seven.stc.masterdata.service;

import org.springframework.stereotype.Service;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.masterdata.models.Location;
import vn.seven.stc.masterdata.repositories.LocationRepository;

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
        List<Location> list = new ArrayList<>();
        for (Map.Entry<String, Location> item: locationMap.entrySet()){
            if (!locationRepository.existsByCode(item.getValue().getCode())){
                beforeCreate(item.getValue());
                list.add(item.getValue());
            }
        }
        if(list.size() > 0){
            locationRepository.save(list);
        }
    }
}
