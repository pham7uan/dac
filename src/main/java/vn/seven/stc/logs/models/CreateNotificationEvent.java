package vn.seven.stc.logs.models;

import java.util.*;

public class CreateNotificationEvent {
    public Long senderId;
    public Set<Long> recipients = new HashSet<>();
    public String actionType;
    public List<String> channels = new ArrayList<>();
    public Long noteId;
    public Long auditLogId;
    public Map<String,String> parameters = new LinkedHashMap<>();
    public Long objectId;
    public String objectType;

    public CreateNotificationEvent() {
    }

    public CreateNotificationEvent(Long senderId, Set<Long> recipients, String actionType, List<String> channels, Long noteId, Long auditLogId, Map<String, String> parameters, Long objectId, String objectType) {
        this.senderId = senderId;
        this.recipients = recipients;
        this.actionType = actionType;
        this.channels = channels;
        this.noteId = noteId;
        this.auditLogId = auditLogId;
        this.parameters = parameters;
        this.objectId = objectId;
        this.objectType = objectType;
    }
}
