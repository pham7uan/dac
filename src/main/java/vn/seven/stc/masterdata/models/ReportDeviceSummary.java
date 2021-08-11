package vn.seven.stc.masterdata.models;

/**
 * Created by hiepnd
 * Date: 11/08/2021
 * Time: 11:29 AM
 * Contact me via mail hiepnd@vnpt-technology.vn
 */
public class ReportDeviceSummary {
    private String areaId;
    private String areaName;
    private String contract;
    private Integer totalDevice;
    private Integer totalPricing;
    private Integer totalNonePricing;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

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

    public Integer getTotalDevice() {
        return totalDevice;
    }

    public void setTotalDevice(Integer totalDevice) {
        this.totalDevice = totalDevice;
    }

    public Integer getTotalPricing() {
        return totalPricing;
    }

    public void setTotalPricing(Integer totalPricing) {
        this.totalPricing = totalPricing;
    }

    public Integer getTotalNonePricing() {
        return totalNonePricing;
    }

    public void setTotalNonePricing(Integer totalNonePricing) {
        this.totalNonePricing = totalNonePricing;
    }
}
