package vn.seven.stc.masterdata.repositories;

import org.springframework.stereotype.Repository;
import vn.seven.stc.core.CustomJpaRepository;
import vn.seven.stc.masterdata.models.Customer;

import java.util.List;

/**
 * Created by hiepnd
 * Date: 12/08/2021
 * Time: 10:48 AM
 * Contact me via mail hiepnd@vnpt-technology.vn
 */
@Repository
public interface CustomerRepository extends CustomJpaRepository<Customer, Long> {
    Boolean existsByCustomerCode(String customerCode);
}
