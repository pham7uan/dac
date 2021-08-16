package vn.seven.stc.masterdata.endpoints;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.seven.stc.core.CrudApiEndpoint;
import vn.seven.stc.masterdata.models.Customer;
import vn.seven.stc.masterdata.models.Device;
import vn.seven.stc.masterdata.service.AreaService;
import vn.seven.stc.masterdata.service.CustomerService;

/**
 * Created by hiepnd
 * Date: 13/08/2021
 * Time: 3:06 PM
 * Contact me via mail hiepnd@vnpt-technology.vn
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerEndPoint extends CrudApiEndpoint<Customer, Long> {

    private CustomerService customerService;

    public CustomerEndPoint(CustomerService service) {
        super(service);
        this.customerService = service;
        this.baseUrl = "/api/customer";
    }
}
