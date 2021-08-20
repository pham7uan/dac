package vn.seven.stc.masterdata.repositories;

import org.springframework.stereotype.Repository;
import vn.seven.stc.core.CustomJpaRepository;
import vn.seven.stc.masterdata.models.Contract;

@Repository
public interface ContractRepository extends CustomJpaRepository<Contract, Long> {
}