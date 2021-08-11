package vn.seven.stc.masterdata.models;

/**
 * Created by hiepnd
 * Date: 11/08/2021
 * Time: 3:55 PM
 * Contact me via mail hiepnd@vnpt-technology.vn
 */
public class ReportDevicepricingCode {
    private String areaName;
    private String contract;
    private String pricingCode;
    private Integer totalNumberPackgeType;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getPricingCode() {
        return pricingCode;
    }

    public void setPricingCode(String pricingCode) {
        this.pricingCode = pricingCode;
    }

    public Integer getTotalNumberPackgeType() {
        return totalNumberPackgeType;
    }

    public void setTotalNumberPackgeType(Integer totalNumberPackgeType) {
        this.totalNumberPackgeType = totalNumberPackgeType;
    }
}
