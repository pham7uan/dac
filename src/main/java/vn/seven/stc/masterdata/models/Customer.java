package vn.seven.stc.masterdata.models;

import vn.seven.stc.core.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by hiepnd
 * Date: 12/08/2021
 * Time: 10:16 AM
 * Contact me via mail hiepnd@vnpt-technology.vn
 */
@Entity
@Table(name = "master_customer")
public class Customer extends IdEntity {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
