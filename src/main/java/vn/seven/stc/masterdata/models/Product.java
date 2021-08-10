package vn.seven.stc.masterdata.models;

import vn.seven.stc.core.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "master_product")
public class Product extends IdEntity {
    private String internalReference;

    public String getInternalReference() {
        return internalReference;
    }

    public void setInternalReference(String internalReference) {
        this.internalReference = internalReference;
    }
}
