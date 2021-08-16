package vn.seven.stc.masterdata.service;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.RSQLParserException;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.core.rsql.CustomRsqlVisitor;
import vn.seven.stc.masterdata.models.*;
import vn.seven.stc.masterdata.repositories.DeviceRepository;

import java.util.*;

@Service
public class DeviceService extends CrudService<Device, Long> {
    private DeviceRepository deviceRepository;
    private CustomerService customerService;
    private PricingService pricingService;
    private LocationService locationService;

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
}

