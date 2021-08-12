package vn.seven.stc.masterdata.repositories;

import org.springframework.stereotype.Repository;
import vn.seven.stc.core.CustomJpaRepository;
import vn.seven.stc.masterdata.models.Pricing;

@Repository
public interface PricingRepository extends CustomJpaRepository<Pricing, Long> {
    Boolean existsByCode(String pricingCode);
}
