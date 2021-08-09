package vn.seven.stc.inventory.repositories;


import org.springframework.stereotype.Repository;
import vn.seven.stc.core.CustomJpaRepository;
import vn.seven.stc.inventory.models.TransferItem;

@Repository
public interface TransferItemRepository extends CustomJpaRepository<TransferItem,Long> {

}
