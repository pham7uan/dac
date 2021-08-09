package vn.seven.stc.masterdata.repositories;

import org.springframework.stereotype.Repository;
import vn.seven.stc.core.CustomJpaRepository;
import vn.seven.stc.masterdata.models.Product;

@Repository
public interface ProductRepository extends CustomJpaRepository<Product, Long> {

}
