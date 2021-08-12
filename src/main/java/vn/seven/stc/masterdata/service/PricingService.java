package vn.seven.stc.masterdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.masterdata.models.Customer;
import vn.seven.stc.masterdata.models.Pricing;
import vn.seven.stc.masterdata.repositories.PricingRepository;
import vn.seven.stc.umgr.utils.SecurityUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PricingService  extends CrudService<Pricing, Long>{
    private PricingRepository pricingRepository;

    public PricingService(PricingRepository repository){
        this.pricingRepository = repository;
    }

    public void create(Map<String, Pricing> pricingMap){
        List<Pricing> list = new ArrayList<>();
        for (Map.Entry<String, Pricing> item: pricingMap.entrySet()){
            if (!pricingRepository.existsByPricingCode(item.getValue().getCode())){
                beforeCreate(item.getValue());
                list.add(item.getValue());
            }
        }
        if(list.size() > 0){
            pricingRepository.save(list);

        }

    }
}
