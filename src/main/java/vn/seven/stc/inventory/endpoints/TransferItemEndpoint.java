package vn.seven.stc.inventory.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.seven.stc.core.CrudApiEndpoint;
import vn.seven.stc.inventory.models.TransferItem;
import vn.seven.stc.inventory.services.TransferItemService;

@RestController
@RequestMapping("/api/transfer-items")
public class TransferItemEndpoint extends CrudApiEndpoint<TransferItem,Long> {

    private TransferItemService transferItemService;

    @Autowired
    public TransferItemEndpoint(TransferItemService service){
        super(service);
        this.transferItemService = service;
        this.baseUrl = "/api/transfer-items";
    }
}
