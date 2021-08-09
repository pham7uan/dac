package vn.seven.stc.logs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import vn.seven.stc.core.filters.ErpApiContext;
import vn.seven.stc.logs.models.*;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.core.utils.Common;
import vn.seven.stc.logs.converters.CustomValueChange;
import vn.seven.stc.logs.repositories.AuditLogRepository;
import vn.seven.stc.umgr.models.User;
import vn.seven.stc.umgr.services.UserService;

import java.util.*;

@Service
public class AuditLogService extends CrudService<AuditLog,Long> {
    @Value("${baseUrl}")
    private String baseUrl;
    private static final Logger log = LoggerFactory.getLogger(AuditLogService.class);
    private AuditLogRepository auditLogRepository;
    private SpringTemplateEngine templateEngine;
    private NoteService noteService;
    private final MessageSource messageSource;
    private PropertyValueHelper propertyValueHelper;
    private UserService userService;
    private NotificationService notificationService;

    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPropertyValueHelper(PropertyValueHelper propertyValueHelper) {
        this.propertyValueHelper = propertyValueHelper;
    }

    @Autowired
    public void setTemplateEngine(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Autowired
    public void setNoteService(NoteService noteService) {
        this.noteService = noteService;
    }

    public AuditLogService(AuditLogRepository auditLogRepository,MessageSource messageSource) {
        this.repository = this.auditLogRepository = auditLogRepository;
        this.messageSource = messageSource;
    }

    @Override
    protected void beforeCreate(AuditLog entity) {
        super.beforeCreate(entity);
        if(entity.getRequestId() == null){
            entity.setRequestId(ErpApiContext.getId());
        }
        if(entity.getChanges() !=null){
            for(CustomValueChange change : entity.getChanges()) {
                if(change.propertyName.contains("Date")){
                    change.oldValue = Common.convertDateTime(change.oldValue);
                    change.newValue = Common.convertDateTime(change.newValue);
                    continue;
                }
                try {
                    change.oldValue = propertyValueHelper.getFkValue(change.propertyName,change.oldValue);
                    change.newValue = propertyValueHelper.getFkValue(change.propertyName,change.newValue);
                } catch (Exception e) {
                    continue;
                }
            }
        }
    }

    @Override
    protected void afterCreate(AuditLog entity){
        super.afterCreate(entity);
        //notification
        User user = userService.findByEmail(entity.getCreatedBy());
        CreateNotificationEvent event = new CreateNotificationEvent(user.getId(),entity.getRecipients(),entity.getActionType(),null,entity.getNoteId(),entity.getId(),entity.getParameters(), entity.getObjectId(), entity.getObjectType());
//        notificationService.send(event);
    }

    @Override
    public Page<AuditLog> search(String query, Pageable pageable) {
        Page<AuditLog> auditLogs = super.search(query, pageable);
        if(auditLogs !=null){
            for(AuditLog auditLog : auditLogs.getContent()) {
                User user  = userService.findByEmail(auditLog.getCreatedBy());
                auditLog.setUserAvatar(user.getUserAvatar());
                List<AuditLog> relateLogs = auditLogRepository.findAllByParentIdAndRequestIdOrderByCreatedDesc(auditLog.getObjectId(),auditLog.getRequestId());
                if(relateLogs == null || relateLogs.size() == 0) { relateLogs = null;}
                auditLog.setRelateLogs(relateLogs);
                if(auditLog.getActionType() !=null){
                    Context context = new Context();
                    context.setLocale(Locale.ENGLISH);
                    if(ActionType.ADD_NOTE.equals(auditLog.getActionType())){
                        Map<String,String> parameters = new HashMap<>();
                        Note note = noteService.get(auditLog.getNoteId());
                        if(note.getContent()!=null){
                            parameters.put("note",note.getContent());
                        }
                        context.setVariables(parameters);
                        if(note.getHasAttachment() !=null && note.getHasAttachment()){
                            Map<String,List<Attachment>> attachments = new HashMap<String,List<Attachment>>(){{
                                put("attachments",note.getAttachments());
                            }};
                            context.setVariables(attachments);
                        }
                    } else {
                        if(auditLog.getChanges() !=null){
                            List<CustomValueChange> objectChanges = new ArrayList(auditLog.getChanges());
                            Map<String,List<CustomValueChange>> changes = new LinkedHashMap<>();
                            for(CustomValueChange c: objectChanges){
                                try{
                                    String fieldName  = messageSource.getMessage(c.propertyName, null, Locale.ENGLISH);
                                    c.propertyName = fieldName;
                                } catch (Exception e){
                                    continue;
                                }
                            }
                            changes.put("changes",objectChanges);
                            context.setVariables(changes);
                        }
                        if(auditLog.getParameters() !=null){
                            context.setVariables(auditLog.getParameters());
                        }
                    }
                    auditLog.setContent(templateEngine.process("log/"+auditLog.getActionType(),context));
                }
            }
        }
        return auditLogs;
    }

    @Override
    public AuditLog get(Long id){
        AuditLog origins = repository.findOne(id);
        AuditLog auditLog = new AuditLog();
        BeanUtils.copyProperties(origins,auditLog);
        if(auditLog !=null){
            List<AuditLog> relateLogs = auditLogRepository.findAllByParentIdAndRequestIdOrderByCreatedDesc(auditLog.getObjectId(),auditLog.getRequestId());
            if(relateLogs == null || relateLogs.size() == 0) { relateLogs = null;}
            auditLog.setRelateLogs(relateLogs);
        }
        return auditLog;
    }

    public AuditLog findByObjectIdAndObjectTypeAndRequestId(Long objectId, String objectType, String requestId){
        return auditLogRepository.findFirstByObjectIdAndObjectTypeAndRequestId(objectId,objectType,requestId);
    }

    public AuditLog findByParentIdAndObjectTypeAndRequestId(Long parentId, String objectType, String requestId){
        return auditLogRepository.findFirstByParentIdAndObjectTypeAndRequestId(parentId,objectType,requestId);
    }

    public List<AuditLog> findAllByParentIdAndObjectTypeAndActionTypeAndRequestId(Long parentId, String objectType,String actionType, String requestId){
        return auditLogRepository.findAllByParentIdAndObjectTypeAndActionTypeAndRequestId(parentId,objectType,actionType,requestId);
    }

    public List<AuditLog> findAllByParentIdAndObjectTypeAndActionTypeAndObjectIdNotInOrderByCreatedAsc(Long parentId, String objectType, String actionType,Set<Long> objectIds){
        return auditLogRepository.findAllByParentIdAndObjectTypeAndActionTypeAndObjectIdNotInOrderByCreatedAsc(parentId,objectType,actionType,objectIds);
    }

    public Set<Long> findObjectIds(Long parentId, String objectType, String actionType){
        return auditLogRepository.findObjectIds(parentId,objectType,actionType);
    }
    public List<AuditLog> findAllByObjectType(String objectType) {
        return auditLogRepository.findAllByObjectType(objectType);
    }
}
