package vn.seven.stc.masterdata.service;

import org.springframework.stereotype.Service;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.masterdata.models.Contract;
import vn.seven.stc.masterdata.repositories.ContractRepository;

@Service
public class ContractService extends CrudService<Contract, Long> {
    private ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.repository = this.contractRepository = contractRepository;
    }
}