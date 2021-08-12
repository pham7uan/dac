package vn.seven.stc.masterdata.models;

import vn.seven.stc.core.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "master_pricing")
public class Pricing extends IdEntity {
    private String cycle;
    private String code;

    public Pricing() {
    }

    public Pricing(Device device) {
        setCode(device.getPricingCode());
        setCycle(device.getPricingCycle());
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
