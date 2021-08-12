package vn.seven.stc.masterdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.masterdata.models.Pricing;
import vn.seven.stc.masterdata.repositories.PricingRepository;
import vn.seven.stc.umgr.utils.SecurityUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PricingService {
    private PricingRepository packingRepository;

    @Autowired
    public void setPackingRepository(PricingRepository packingRepository) {
        this.packingRepository = packingRepository;
    }

    public void create(Map<String, Pricing> pricingMap){
        List<Pricing> piercings = new ArrayList<>();
        Pricing pricing;
        for (Map.Entry<String, Pricing> item: pricingMap.entrySet()){
            if (!packingRepository.existsByPricingCode(item.getValue().getCode())){
                pricing = new Pricing();
                pricing.setCode(item.getValue().getCode());
                pricing.setCycle(item.getValue().getCycle());
                pricing.setCreated(System.currentTimeMillis());
                if(pricing.getCreatedBy() == null) {
                    String currentUsername = SecurityUtils.getCurrentUserLogin();
                    pricing.setCreatedBy(currentUsername);
                }
                piercings.add(pricing);
            }
        }
        packingRepository.save(piercings);

    }
}
