package vn.seven.stc.logs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.seven.stc.logs.models.*;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.logs.repositories.AttachmentRepository;
import vn.seven.stc.logs.repositories.NoteRepository;
import vn.seven.stc.umgr.repositories.UserRepository;


@Service
public class NoteService extends CrudService<Note,Long> {
    @Value("${baseUrl}")
    private String baseUrl;
    private NoteRepository noteRepository;
    private UserRepository userRepository;
    private AuditLogService auditLogService;
    private NotificationService notificationService;
    private AttachmentRepository attachmentRepository;

    @Autowired
    public void setNotificationService(NotificationService notificationService,
                                       UserRepository userRepository,
                                       AttachmentRepository attachmentRepository) {
        this.notificationService = notificationService;
        this.userRepository = userRepository;
        this.attachmentRepository = attachmentRepository;
    }

    @Autowired
    public void setTransferDetailsService(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    public NoteService(NoteRepository repository) {
        this.repository = this.noteRepository = repository;
    }
//
//    @Override
//    protected void afterCreate(Note entity) {
//        super.afterCreate(entity);
//        if(entity.getHasAttachment() == null || entity.getHasAttachment() == false){
//            sendNotification(entity);
//        }
//    }
//
//    private void sendNotification(Note entity) {
//        CreateNotificationEvent event = new CreateNotificationEvent();
//        //send mention notification
//        event.channels.add("onsite");
//        event.actionType = ActionType.ADD_NOTE;
//        if(entity.getMentionedIds().size() > 0) {
//            event.actionType = ActionType.MENTIONED_IN_NOTE;
//        }
//        event.noteId = entity.getId();
//        User user = userRepository.findOneByAccountIgnoreCase(entity.getCreatedBy());
//        event.senderId = user.getId();
//        event.recipients = entity.getMentionedIds();
//
//        String sender = user.getFullName();
//        Map<String,String > parameters  = new LinkedHashMap<>();
//        Set<Long> recipients = new HashSet<>();
//        event.recipients.remove(user.getId());
//        event.parameters= parameters;
//        event.objectId = entity.getObjectId();
//        event.objectType = entity.getObjectType();
//        notificationService.send(event);
//
//        //save log
//        AuditLog auditLog = new AuditLog();
//        recipients.remove(user.getId());
//        recipients.removeAll(event.recipients);
//        auditLog.setRecipients(recipients);
//        auditLog.setObjectId(entity.getObjectId());
//        auditLog.setObjectType(entity.getObjectType());
//        auditLog.setActionType(ActionType.ADD_NOTE);
//        auditLog.setActorUserName(sender);
//        auditLog.setNoteId(entity.getId());
//        auditLog.setParameters(parameters);
//        auditLogService.create(auditLog);
//    }
//
//    public void attachFile(Long id, MultipartFile[] files) throws IOException {
//        if(files !=null && files.length >0){
//            Note note = noteRepository.findOne(id);
//            String resource = System.getProperty("user.dir") + "/attachments/" + note.getId() + "/";
//            Common.createDirectory(resource);
//            for(int i =0; i < files.length; i++){
//                MultipartFile file = files[i];
//                String originName = file.getOriginalFilename();
//                File convFile = new File(resource + originName);
//                String fileName = originName.replace(" ","_");
//                if(convFile.exists()){
//                    fileName = Common.renameDuplicateFile(fileName);
//                    convFile = new File(resource + fileName);
//                }
//                file.transferTo(convFile);
//                Attachment att = new Attachment(originName,"/attachments/" + note.getId() + "/" + fileName,id);
//                attachmentRepository.save(att);
//                note.setHasAttachment(true);
//            }
//
//            update(id,note);
//            sendNotification(note);
//        }
//
//    }
//
//    public Map<String,String> attachFile(String reference, MultipartFile file) throws IOException {
//        Map<String,String> result = new HashedMap<>();
//        String resource = System.getProperty("user.dir") + "/attachments/" + reference + "/";
//        Common.createDirectory(resource);
//        String originName = file.getOriginalFilename();
//
//        String fileName = originName.replace(" ","_");
//        File convFile = new File(resource + fileName);
//        if(convFile.exists()){
//            fileName = Common.renameDuplicateFile(fileName);
//            convFile = new File(resource + fileName);
//        }
//        file.transferTo(convFile);
//
//        result.put("name",originName);
//        result.put("url","/attachments/" + reference + "/" + fileName);
//        result.put("api",baseUrl + "/api/download?filePath=" + System.getProperty("user.dir") + "/attachments/" + reference + "/" + fileName);
//        return result;
//
//
//    }
//
//    @Override
//    public Note get(Long id) {
//        Note note = noteRepository.findOne(id);
//        List<Attachment>  attachments = attachmentRepository.findAllByParentId(id);
//        List<Attachment> clones = new LinkedList<>();
//        if(attachments !=null){
//            for(Attachment attachment:attachments){
//                Attachment clone = new Attachment(attachment.getName(),attachment.getUrl(),attachment.getParentId());
//                clone.setUrl(baseUrl + "/api/download?filePath=" + System.getProperty("user.dir") + attachment.getUrl());
//                clones.add(clone);
//            }
//            note.setAttachments(clones);
//        }
//        return note;
//    }
}
