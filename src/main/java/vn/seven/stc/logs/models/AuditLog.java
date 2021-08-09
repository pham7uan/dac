package vn.seven.stc.logs.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import vn.seven.stc.core.IdEntity;
import vn.seven.stc.core.utils.MapConverter;
import vn.seven.stc.logs.converters.CustomValueChange;
import vn.seven.stc.logs.converters.ValueChangesConverter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "base_audit_log")
public class AuditLog extends IdEntity {
    private String requestId;
    private String objectType;
    private Long objectId;
    private Long parentId;
    private String actorUserName;
    private String actionType;
    private Long noteId;
    @Convert(converter = ValueChangesConverter.class)
    private List<CustomValueChange> changes;
    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String content;
    @Convert(converter = MapConverter.class)
    private Map<String,String> parameters = new LinkedHashMap<>();
    @Transient
    public Set<Long> recipients = new HashSet<>();

    @Lob
    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String userAvatar;

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    List<AuditLog> relateLogs;

    public AuditLog() {
        super();
    }
    public AuditLog(Class<?> clazz, Long objectId, String actorUserName, String actionType, List<CustomValueChange> changes, Map<String,String> parameters,Set<Long> recipients) {
        this.objectType = clazz.getSimpleName();
        this.objectId = objectId;
        this.actorUserName = actorUserName;
        this.actionType = actionType;
        this.changes = changes;
        this.parameters = parameters;
        this.recipients = recipients;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getActorUserName() {
        return actorUserName;
    }

    public void setActorUserName(String actorUserName) {
        this.actorUserName = actorUserName;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public List<CustomValueChange> getChanges() {
        return changes;
    }

    public void setChanges(List<CustomValueChange> changes) {
        this.changes = changes;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public Set<Long> getRecipients() {
        return recipients;
    }

    public void setRecipients(Set<Long> recipients) {
        this.recipients = recipients;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public List<AuditLog> getRelateLogs() {
        return relateLogs;
    }

    public void setRelateLogs(List<AuditLog> relateLogs) {
        this.relateLogs = relateLogs;
    }
}
