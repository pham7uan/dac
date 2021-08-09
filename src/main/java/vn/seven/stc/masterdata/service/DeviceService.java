package vn.seven.stc.masterdata.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.masterdata.models.Device;
import vn.seven.stc.masterdata.models.Product;
import vn.seven.stc.masterdata.repositories.DeviceRepository;
import vn.seven.stc.masterdata.repositories.ProductRepository;

@Service
public class DeviceService extends CrudService<Device, Long> {
    private DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository){
        this.repository = this.deviceRepository = deviceRepository;
    }
}

