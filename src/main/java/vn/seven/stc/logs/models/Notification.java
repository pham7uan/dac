package vn.seven.stc.logs.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import vn.seven.stc.core.IdEntity;
import vn.seven.stc.core.utils.MapConverter;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
@Table(name = "base_notifications")
public class Notification extends IdEntity {

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String content;
    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String objectUrl;
    private Boolean isRead = Boolean.FALSE;
    private Long senderId;
    private Long recipientId;
    @Convert(converter = MapConverter.class)
    private Map<String,String> parameters = new LinkedHashMap<>();
    private String actionType;
    private Long noteId;
    private Long templateId;
    private Long auditLogId;
    private Long objectId;
    private String objectType;

    private Boolean isShowPopup = Boolean.FALSE;
    private Boolean isShowDropdown = Boolean.FALSE;

    public Boolean getShowPopup() {
        return isShowPopup;
    }

    public void setShowPopup(Boolean showPopup) {
        isShowPopup = showPopup;
    }

    public Boolean getShowDropdown() {
        return isShowDropdown;
    }

    public void setShowDropdown(Boolean showDropdown) {
        isShowDropdown = showDropdown;
    }

    @Lob
    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String userAvatar;

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public Long getAuditLogId() {
        return auditLogId;
    }

    public void setAuditLogId(Long auditLogId) {
        this.auditLogId = auditLogId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }


    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

//    public String getObjectUrl() {
//        if(objectType !=null && objectId !=null){
//            if(!ActionType.checkActionDelete(actionType)) {
//                StringBuilder url = new StringBuilder("#/");
//                url.append(Constants.OBJECT_STATE.get(objectType)).append("/").append(objectId).append("/details");
//                return url.toString();
//            }
//        }
//        return objectUrl;
//    }

    public void setObjectUrl(String objectUrl) {
        this.objectUrl = objectUrl;
    }
}
