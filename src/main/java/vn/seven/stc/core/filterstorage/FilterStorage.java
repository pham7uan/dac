package vn.seven.stc.core.filterstorage;


import com.fasterxml.jackson.annotation.JsonProperty;
import vn.seven.stc.core.IdEntity;
import vn.seven.stc.core.utils.MapConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "base_filters")
public class FilterStorage extends IdEntity {
    private String name;
    private String model;
    @JsonProperty("isPrivate")
    private Boolean isPrivate;
    @Convert(converter = MapConverter.class)
    private Map<String,Object> filter = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        this.isPrivate = aPrivate;
    }

    public Map<String, Object> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, Object> filter) {
        this.filter = filter;
    }
}
