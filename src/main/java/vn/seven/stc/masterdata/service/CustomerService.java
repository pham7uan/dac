package vn.seven.stc.masterdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.masterdata.models.Customer;
import vn.seven.stc.masterdata.repositories.CustomerRepository;
import vn.seven.stc.masterdata.repositories.DeviceRepository;
import vn.seven.stc.umgr.utils.SecurityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by hiepnd
 * Date: 12/08/2021
 * Time: 1:53 PM
 * Contact me via mail hiepnd@vnpt-technology.vn
 */
@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void create(Set<String> customerCode){
        List<Customer> customers = new ArrayList<>();
        for (String code : customerCode){
            if (!customerRepository.existsByCustomerCode(code)){
                customers.add(new Customer(code));
            }
        }
        if(customers.size() > 0){
            customerRepository.save(customers);
        }
    }
}
