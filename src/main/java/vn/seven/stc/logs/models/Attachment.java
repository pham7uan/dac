package vn.seven.stc.logs.models;

import vn.seven.stc.core.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "base_attachments")
public class Attachment extends IdEntity {
    private String name;
    private String url;
    private Long parentId;
    private String model;
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Attachment() {
    }

    public Attachment(String name, String url, Long parentId) {
        this.name = name;
        this.url = url;
        this.parentId = parentId;
    }

    public Attachment(String name, String url, Long parentId, String model) {
        this.name = name;
        this.url = url;
        this.parentId = parentId;
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
