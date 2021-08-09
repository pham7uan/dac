package vn.seven.stc.inventory.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.inventory.models.Transfer;
import vn.seven.stc.inventory.repositories.TransferRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@Transactional
@Component
public class TransferService extends CrudService<Transfer,Long> {
    private static Logger logger = LoggerFactory.getLogger(TransferService.class);
    @Value("${baseUrl}")
    private String baseUrl;
    private TransferRepository transferRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public TransferService(TransferRepository repository) {
        this.repository = this.transferRepository = repository;
    }

}




