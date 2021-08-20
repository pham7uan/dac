package vn.seven.stc.masterdata.service;

import org.springframework.beans.BeanUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.seven.stc.core.ApiResponse;
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

    public ApiResponse update(List<DevicePubic> devicePubic, ApiResponse apiResponse){
        // validate data
        if(validateDevicePublic(devicePubic,apiResponse)){
            // prepare data
            Map<String, DevicePubic> pubicMap = prepareData(devicePubic);
            // save data
            save(pubicMap);
        };
        return apiResponse;
    }

    private Map<String, DevicePubic> prepareData(List<DevicePubic> devicePubic){
        Map<String, DevicePubic> pubicMap = new HashMap<>();    // map các serial được cập nhật
        Set<String> customerCode = new HashSet<>();
        Map<String, Pricing> pricingMap = new HashMap<>();
        Map<String, Location> locationMap = new HashMap<>();
        for(DevicePubic pub: devicePubic){
            pubicMap.put(pub.getSerial(), pub);
            customerCode.add(pub.getCustomerCode());
            pricingMap.put(pub.getPricingCode(),  new Pricing(pub));
            locationMap.put(pub.getLocationCode(), new Location(pub));
        }
        customerService.create(customerCode);
        pricingService.create(pricingMap);
        locationService.create(locationMap);
        return pubicMap;
    }

    private void save(Map<String, DevicePubic> pubicMap){
        Set<String> serials = new HashSet<String>(pubicMap.keySet());    // lấy danh sách serial từ map
        List<Device> devices = deviceRepository.findAllBySerialIn(serials);  // Lấy danh sách device từ Database
        if(devices.size() > 0){
            for(Device d: devices){
                DevicePubic pubic = pubicMap.get(d.getSerial());
                BeanUtils.copyProperties(pubic,d);                              // Cập nhật thông tin
                beforeUpdate(d);
            }
            deviceRepository.save(devices);
        }
    }

    private Boolean validateDevicePublic(List<DevicePubic> devicePubic, ApiResponse apiResponse){
        return true;
    }

    public Map<String,String> exportDevice(List<Device> devices, String column) throws IOException {
        Map<String,String> fileName = new HashedMap<>();
        fileName.put("fileName",deviceExport.exportExcel(devices, column));
        return fileName;
    }
}

