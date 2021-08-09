package vn.seven.stc.logs.services;

import org.springframework.stereotype.Service;
import vn.seven.stc.logs.models.Attachment;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.logs.repositories.AttachmentRepository;
import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class AttachmentService extends CrudService<Attachment,Long> {
    private AttachmentRepository attachmentRepository;
    public AttachmentService(AttachmentRepository repository) {
        this.repository = this.attachmentRepository = repository;
    }

    public List<Attachment> findAllByParentIdAndModel(Long id,String model){
        return attachmentRepository.findAllByParentIdAndModel(id,model);
    }

    public void deleteAllByParentIdAndModel(Long id, String model){
        attachmentRepository.deleteAllByParentIdAndModel(id,model);
    }
}
