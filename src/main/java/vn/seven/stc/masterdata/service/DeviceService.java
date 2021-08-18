package vn.seven.stc.masterdata.service;


import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.masterdata.models.*;
import vn.seven.stc.masterdata.repositories.DeviceRepository;

import java.io.IOException;
import java.util.*;

@Service
public class DeviceService extends CrudService<Device, Long> {
    private DeviceRepository deviceRepository;
    private CustomerService customerService;
    private PricingService pricingService;
    private LocationService locationService;
    private DeviceExport deviceExport;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setPricingService(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @Autowired
    public void setDeviceExport(DeviceExport deviceExport) {
        this.deviceExport = deviceExport;
    }

    public DeviceService(DeviceRepository deviceRepository){
        this.repository = this.deviceRepository = deviceRepository;
    }

    public void update(List<Device> devices){
        Set<String> customerCode = new HashSet<>();
        Map<String, Pricing> pricingMap = new HashMap<>();
        Map<String, Location> locationMap = new HashMap<>();
        for(Device device: devices){
            beforeUpdate(device);
            // todo: get list customer code, pricing Map, locationMap
            customerCode.add(device.getCustomerCode());
            pricingMap.put(device.getPricingCode(),  new Pricing(device));
            locationMap.put(device.getLocationCode(), new Location(device));
        }

        repository.save(devices);

        // todo: create new customer
        customerService.create(customerCode);
        // todo: create new Pricing
        pricingService.create(pricingMap);
        // todo: create new location
        locationService.create(locationMap);
    }

    public Map<String,String> exportDevice(List<Device> devices) throws IOException {
        Map<String,String> fileName = new HashedMap<>();
        fileName.put("fileName",deviceExport.exportExcel(devices));
        return fileName;
    }
}

