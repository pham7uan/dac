package vn.seven.stc.masterdata.endpoints;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.seven.stc.core.CrudApiEndpoint;
import vn.seven.stc.masterdata.models.Contract;
import vn.seven.stc.masterdata.service.ContractService;

@RestController
@RequestMapping("/api/contract")
public class ContractEndpoint extends CrudApiEndpoint<Contract, Long> {
    private ContractService contractService;

    public ContractEndpoint(ContractService service) {
        super(service);
        this.contractService = service;
        this.baseUrl = "/api/contract";
    }
}