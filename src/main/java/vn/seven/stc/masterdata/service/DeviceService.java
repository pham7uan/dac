package vn.seven.stc.masterdata.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.masterdata.models.*;
import vn.seven.stc.masterdata.repositories.DeviceRepository;
import vn.seven.stc.masterdata.repositories.ProductRepository;

import java.util.*;

@Service
public class DeviceService extends CrudService<Device, Long> {
    private DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository){
        this.repository = this.deviceRepository = deviceRepository;
    }

    public void updateInformation(List<Device> devices){
        repository.save(devices);

        Set<String> customerCode = new HashSet<>();
        Map<String, Pricing> pricingMap = new HashMap<>();
        Map<String, Location> locationMap = new HashMap<>();
        for(Device device: devices){
            // todo: get list customer code, pricing Map, locationMap
        }
        // todo: create new customer
        // todo: create new Pricing
        // todo: create new location
    }
}

