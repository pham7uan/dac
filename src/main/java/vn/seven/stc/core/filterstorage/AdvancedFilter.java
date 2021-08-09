package vn.seven.stc.core.filterstorage;

import com.fasterxml.jackson.annotation.JsonProperty;
import vn.seven.stc.core.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "base_advanced_filters")
public class AdvancedFilter  extends IdEntity {
    private Long configId;
    private Integer groupId;
    private Long userId;
    private String page;
    private Long objectId;
    private String field;
    private String operator;
    private String value;
    private String label;
    private String type;
    private String model;
    private String originalField;
    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean selected;
    private Boolean specialFilter;

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOriginalField() {
        return originalField;
    }

    public void setOriginalField(String originalField) {
        this.originalField = originalField;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Boolean getSpecialFilter() {
        return specialFilter;
    }

    public void setSpecialFilter(Boolean specialFilter) {
        this.specialFilter = specialFilter;
    }
}
