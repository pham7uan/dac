package vn.seven.stc.inventory.services;

import org.springframework.stereotype.Service;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.inventory.models.TransferItem;
import vn.seven.stc.inventory.repositories.TransferItemRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class TransferItemService extends CrudService<TransferItem,Long> {
    private TransferItemRepository transferItemRepository;

    public TransferItemService(TransferItemRepository repository) {
        this.repository = this.transferItemRepository = repository;
    }
}
