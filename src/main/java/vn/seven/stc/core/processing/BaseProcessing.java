package vn.seven.stc.core.processing;


import vn.seven.stc.core.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "base_processing")
public class BaseProcessing extends IdEntity {
    private String reference;
    private Long objectId;
    private String objectType;
    private Integer processing;
    private String log;
    private String threadSync;

    public String getThreadSync() {
        return threadSync;
    }

    public void setThreadSync(String threadSync) {
        this.threadSync = threadSync;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
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

    public Integer getProcessing() {
        return processing;
    }

    public void setProcessing(Integer processing) {
        this.processing = processing;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
