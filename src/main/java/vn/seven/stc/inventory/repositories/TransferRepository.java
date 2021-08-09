package vn.seven.stc.inventory.repositories;


import org.springframework.stereotype.Repository;
import vn.seven.stc.core.CustomJpaRepository;
import vn.seven.stc.inventory.models.Transfer;

@Repository
public interface TransferRepository extends CustomJpaRepository<Transfer,Long> {

}
