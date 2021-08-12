package vn.seven.stc.masterdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.masterdata.models.*;
import vn.seven.stc.masterdata.repositories.CustomerRepository;
import vn.seven.stc.masterdata.repositories.DeviceRepository;
import vn.seven.stc.masterdata.repositories.LocationRepository;
import vn.seven.stc.masterdata.repositories.PricingRepository;
import vn.seven.stc.umgr.utils.SecurityUtils;

import java.util.*;

@Service
public class DeviceService extends CrudService<Device, Long> {
    private DeviceRepository deviceRepository;
    private CustomerRepository customerRepository;
    private PricingRepository pricingRepository;
    private LocationRepository locationRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    public void setPricingRepository(PricingRepository pricingRepository) {
        this.pricingRepository = pricingRepository;
    }

    @Autowired
    public void setLocationRepository(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public DeviceService(DeviceRepository deviceRepository){
        this.repository = this.deviceRepository = deviceRepository;
    }

    public void updateInformation(List<Device> devices){
        repository.save(devices);

        Pricing pricing;
        Location location;
        Set<String> customerCode = new HashSet<>();
        Map<String, Pricing> pricingMap = new HashMap<>();
        Map<String, Location> locationMap = new HashMap<>();
        for(Device device: devices){
            // todo: get list customer code, pricing Map, locationMap
            customerCode.add(device.getCustomerCode());

            pricing = getPricing(device);
            pricingMap.put(device.getPricingCode(), pricing);

            location = getLocation(device);
            locationMap.put(device.getLocationCode(), location);
        }
        // todo: create new customer
        insertCustomer(customerCode);
        // todo: create new Pricing
        insertPricing(pricingMap);
        // todo: create new location
        insertLocation(locationMap);
    }

    private Pricing getPricing(Device device) {
        Pricing pricing = new Pricing();
        pricing.setCode(device.getPricingCode());
        pricing.setCycle(device.getPricingCycle());
        return pricing;
    }

    private Location getLocation(Device device) {
        Location location = new Location();
        location.setName(device.getAreaName());
        location.setCode(device.getLocationCode());
        location.setAreaId(device.getAreaId());
        return location;
    }

    public void insertCustomer(Set<String> customerCode){
        List<Customer> customers = new ArrayList<>();
        Customer customer;
        for (String code : customerCode){
            if (!deviceRepository.existsByCustomerCode(code)){
                customer = new Customer();
                customer.setCode(code);
                customer.setCreated(System.currentTimeMillis());
                if(customer.getCreatedBy() == null) {
                    String currentUsername = SecurityUtils.getCurrentUserLogin();
                    customer.setCreatedBy(currentUsername);
                }
                customers.add(customer);
            }
        }
         customerRepository.save(customers);
    }

    public void insertPricing(Map<String, Pricing> pricingMap){
        List<Pricing> piercings = new ArrayList<>();
        Pricing pricing;
        for (Map.Entry<String, Pricing> item: pricingMap.entrySet()){
            if (!deviceRepository.existsByPricingCode(item.getValue().getCode())){
                pricing = new Pricing();
                pricing.setCode(item.getValue().getCode());
                pricing.setCycle(item.getValue().getCycle());
                pricing.setCreated(System.currentTimeMillis());
                if(pricing.getCreatedBy() == null) {
                    String currentUsername = SecurityUtils.getCurrentUserLogin();
                    pricing.setCreatedBy(currentUsername);
                }
                piercings.add(pricing);
            }
        }
        pricingRepository.save(piercings);

    }

    public void insertLocation(Map<String, Location> locationMap){
        List<Location> locations = new ArrayList<>();
        Location location;
        for (Map.Entry<String, Location> item: locationMap.entrySet()){
            if (!deviceRepository.existsByPricingCode(item.getValue().getCode())){
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

