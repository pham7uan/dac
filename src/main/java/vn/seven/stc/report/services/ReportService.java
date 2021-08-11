package vn.seven.stc.report.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.masterdata.models.Device;
import vn.seven.stc.masterdata.repositories.DeviceRepository;
import vn.seven.stc.masterdata.service.DeviceService;

@Service
@Transactional
public class ReportService extends CrudService<Device, Long> {
    @Autowired
    private DeviceRepository deviceRepository;

    public ReportService(DeviceRepository repository){
        this.repository = this.deviceRepository = repository;
    }
}