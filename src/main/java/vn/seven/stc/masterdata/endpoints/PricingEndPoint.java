package vn.seven.stc.masterdata.endpoints;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.seven.stc.core.CrudApiEndpoint;
import vn.seven.stc.masterdata.models.Pricing;
import vn.seven.stc.masterdata.service.CustomerService;
import vn.seven.stc.masterdata.service.PricingService;

/**
 * Created by hiepnd
 * Date: 13/08/2021
 * Time: 3:08 PM
 * Contact me via mail hiepnd@vnpt-technology.vn
 */
@RestController
@RequestMapping("/api/pricing")
public class PricingEndPoint extends CrudApiEndpoint<Pricing, Long> {

    private PricingService pricingService;

    public PricingEndPoint(PricingService service) {
        super(service);
        this.pricingService = service;
        this.baseUrl = "/api/pricing";
    }

}
