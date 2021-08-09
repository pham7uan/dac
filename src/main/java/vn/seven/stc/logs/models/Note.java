package vn.seven.stc.logs.models;

import vn.seven.stc.core.IdEntity;
import vn.seven.stc.core.utils.SetConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.*;

@Entity
@Table(name = "base_notes")
public class Note extends IdEntity {
    private String content;
    private Long objectId;
    private String objectType;
    @Convert(converter = SetConverter.class)
    private Set<Long> mentionedIds;
    @Transient
    private List<Attachment> attachments = new LinkedList<>();

    private Boolean hasAttachment = false;

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Set<Long> getMentionedIds() {
        return mentionedIds;
    }

    public void setMentionedIds(Set<Long> mentionedIds) {
        this.mentionedIds = mentionedIds;
    }

    public Boolean getHasAttachment() {
        return hasAttachment;
    }

    public void setHasAttachment(Boolean hasAttachment) {
        this.hasAttachment = hasAttachment;
    }
}
