package vn.seven.stc.core.processing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.core.errors.BadRequestAlertException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BaseProcessingService extends CrudService<BaseProcessing,Long> {
    private static final Logger logger = LoggerFactory.getLogger(BaseProcessingService.class);
    private BaseProcessingRepository baseProcessingRepository;

    public BaseProcessingService(BaseProcessingRepository repository) {
        this.repository = this.baseProcessingRepository = repository;
    }

    public BaseProcessing findByReference(String reference){
        return baseProcessingRepository.findFirstByReference(reference);
    }

    public BaseProcessing findFirstByObjectIdAndObjectType(Long id, String model){
        return baseProcessingRepository.findFirstByObjectIdAndObjectType(id,model);
    }

    public void finishAllProcess(){
        baseProcessingRepository.finishAllProcess();
    }

    public List<BaseProcessing> findAllByObjectIdAndObjectType(Long id, String type){
        return baseProcessingRepository.findAllByObjectIdAndObjectType(id,type);
    }

    public void finishProcess(Long id){
        baseProcessingRepository.finishProcess(id);
    }

    @Override
    protected void beforeCreate(BaseProcessing entity) {
        super.beforeCreate(entity);
        if(entity.getObjectId() !=null){
            BaseProcessing baseProcessing =findFirstByObjectIdAndObjectType(entity.getObjectId(),entity.getObjectType());
            if(baseProcessing !=null) {
                throw new BadRequestAlertException("Transfer is processing by another", "transfer", "transferInProcessing");
            }
        }

    }
}
