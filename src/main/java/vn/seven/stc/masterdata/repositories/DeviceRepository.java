package vn.seven.stc.masterdata.repositories;

import org.springframework.stereotype.Repository;
import vn.seven.stc.core.CustomJpaRepository;
import vn.seven.stc.masterdata.models.Customer;
import vn.seven.stc.masterdata.models.Device;

import java.util.List;

@Repository
public interface DeviceRepository extends CustomJpaRepository<Device, Long> {

}
