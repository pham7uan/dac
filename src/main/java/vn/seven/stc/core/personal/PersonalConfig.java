package vn.seven.stc.core.personal;


import com.fasterxml.jackson.annotation.JsonProperty;
import vn.seven.stc.core.IdEntity;
import vn.seven.stc.core.filterstorage.AdvancedFilterGroup;
import vn.seven.stc.core.utils.MapConverter;
import vn.seven.stc.core.utils.SetConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.*;

@Entity
@Table(name = "base_personal_configs")
public class PersonalConfig extends IdEntity {
    private String name;
    private String displayName;
    private String email;
    private Long userId;
    private String page;
    private Long objectId;
    private String groupField;
    private Long size;
    private Boolean isPublic = false;
    @Convert(converter = SetConverter.class)
    private Set<Long> customFilterIds = new HashSet<>();
    @Convert(converter = MapConverter.class)
    private Map<String,Object> specialFilters = new HashMap<>();
    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean isDefault = false;

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    List<AdvancedFilterGroup> advancedFilterGroups = new ArrayList<>();

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public List<AdvancedFilterGroup> getAdvancedFilterGroups() {
        return advancedFilterGroups;
    }

    public void setAdvancedFilterGroups(List<AdvancedFilterGroup> advancedFilterGroups) {
        this.advancedFilterGroups = advancedFilterGroups;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getGroupField() {
        return groupField;
    }

    public void setGroupField(String groupField) {
        this.groupField = groupField;
    }

    public Set<Long> getCustomFilterIds() {
        return customFilterIds;
    }

    public void setCustomFilterIds(Set<Long> customFilterIds) {
        this.customFilterIds = customFilterIds;
    }

    public Map<String, Object> getSpecialFilters() {
        return specialFilters;
    }

    public void setSpecialFilters(Map<String, Object> specialFilters) {
        this.specialFilters = specialFilters;
    }
}
