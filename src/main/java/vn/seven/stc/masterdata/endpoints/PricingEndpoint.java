package vn.seven.stc.masterdata.endpoints;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.seven.stc.config.ApplicationProperties;
import vn.seven.stc.core.CrudApiEndpoint;
import vn.seven.stc.masterdata.models.Pricing;
import vn.seven.stc.masterdata.service.PricingService;

@RestController
@RequestMapping("/api/packing")
@EnableConfigurationProperties(ApplicationProperties.class)
public class PricingEndpoint extends CrudApiEndpoint<Pricing, Long> {
    private PricingService packingService;

    public PricingEndpoint(PricingService service){
        super(service);
        this.packingService = service;
        this.baseUrl = "/api/packing";
    }
}
