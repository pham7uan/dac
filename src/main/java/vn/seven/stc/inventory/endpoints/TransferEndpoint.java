package vn.seven.stc.inventory.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.seven.stc.core.CrudApiEndpoint;
import vn.seven.stc.inventory.models.Transfer;
import vn.seven.stc.inventory.services.TransferService;

@RestController
@RequestMapping("/api/transfers")
public class TransferEndpoint extends CrudApiEndpoint<Transfer,Long> {
    private static Logger logger = LoggerFactory.getLogger(TransferEndpoint.class);
    private TransferService transferService;

    @Autowired
    public TransferEndpoint(TransferService service){
        super(service);
        this.transferService = service;
        this.baseUrl = "/api/transfers";
    }


}
