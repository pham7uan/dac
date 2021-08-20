package vn.seven.stc.masterdata.models;

import vn.seven.stc.core.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "master_contract")
public class Contract extends IdEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}