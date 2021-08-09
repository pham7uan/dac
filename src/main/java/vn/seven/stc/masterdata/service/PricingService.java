package vn.seven.stc.masterdata.service;

import org.springframework.stereotype.Service;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.masterdata.models.Pricing;
import vn.seven.stc.masterdata.repositories.PricingRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class PricingService extends CrudService<Pricing, Long> {
    private PricingRepository packingRepository;

    public PricingService(PricingRepository repository){
        this.repository = this.packingRepository = repository;
    }

}
