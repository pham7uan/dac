package vn.seven.stc.masterdata.models;

import vn.seven.stc.core.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by hiepnd
 * Date: 09/08/2021
 * Time: 2:34 PM
 * Contact me via mail hiepnd@vnpt-technology.vn
 */
@Entity
@Table(name = "master_location")
public class Location extends IdEntity {
    private String code;
    private String name;
    private Long areaId;

    public Location() {
    }

    public Location(Device device) {
        setName(device.getAreaName());
        setCode(device.getLocationCode());
        setAreaId(device.getAreaId());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }
}
