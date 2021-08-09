package vn.seven.stc.masterdata.models;

import vn.seven.stc.core.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by hiepnd
 * Date: 09/08/2021
 * Time: 2:24 PM
 * Contact me via mail hiepnd@vnpt-technology.vn
 */
@Entity
@Table(name = "master_area")
public class Area extends IdEntity {
    private String code;
    private String name;

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
}
