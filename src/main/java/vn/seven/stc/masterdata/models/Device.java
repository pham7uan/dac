package vn.seven.stc.masterdata.models;

import vn.seven.stc.core.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "master_devices")
public class Device extends IdEntity {
    private String serial;//1
    private String imei;
    private String mac;
    private String productName;//1
    private String fw;
    private Integer state = DeviceState.EXPORTED;  //trạng thái thiết bị
    private Long areaId;                            // khu vực
    private String areaName;
    private Long exportDate;                        // ngày xuất xưởng
    private String exportCode;                      // mã phiếu xuất kho
    private Long deliveryDate;                      // ngày giao hàng
    private Long activeDate;                        // ngày kích hoạt
    private Long expiredDate;                       // ngày hết hạn bảo hành
    private Long guaranteeExportDate;               // Ngày xuất kho bảo hành
    private Long guaranteeImportDate;               // Ngày nhập kho bảo hành
    private Long recallDate;                        // ngày thu hồi thiết bị
    private String customerCode;                    // Mã khách hàng
    private String pricingCode;                     // gói cước
    private String pricingCycle;                    // Chu kỳ cước
    private Long pricingBeginDate;                  // Ngày bắt đầu gói cước
    private Long pricingEndDate;                    // Ngày thanh lý gói cước
    private Long pricingPauseDate;                  // Ngày tạm ngưng gói cước
    private Long pricingChangeDate;                 // Ngày đôi gói cước (rã gói)
    private String subscriptionStatus;              // Trạng thái thuê bao
    private String originContract;                  // Hợp đồng gốc
    private String originPo;                        // PO gốc
    private String contract;                        // Hợp đồng hiện tại
    private String po;                              // PO hiện tại
    private String originAgency;                    // Đại lý gốc
    private String agency;                          // Đại lý hiện tại
    private String locationCode;                    // Mã kho
    private String locationName;                    // Tên kho
    private String description;                     // Mô tả thêm
    private String accountingCode;                  // Mã số kế toán
    private String inventoryTransferNumber;         // Số phiếu xuất
    private Integer customerType = 1;               //1 Khách hàng hiện hữu/ 2 khách hàng mới

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFw() {
        return fw;
    }

    public void setFw(String fw) {
        this.fw = fw;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Long getExportDate() {
        return exportDate;
    }

    public void setExportDate(Long exportDate) {
        this.exportDate = exportDate;
    }

    public String getExportCode() {
        return exportCode;
    }

    public void setExportCode(String exportCode) {
        this.exportCode = exportCode;
    }

    public Long getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Long deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Long getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(Long activeDate) {
        this.activeDate = activeDate;
    }

    public Long getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Long expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Long getGuaranteeExportDate() {
        return guaranteeExportDate;
    }

    public void setGuaranteeExportDate(Long guaranteeExportDate) {
        this.guaranteeExportDate = guaranteeExportDate;
    }

    public Long getGuaranteeImportDate() {
        return guaranteeImportDate;
    }

    public void setGuaranteeImportDate(Long guaranteeImportDate) {
        this.guaranteeImportDate = guaranteeImportDate;
    }

    public String getInventoryTransferNumber() {
        return inventoryTransferNumber;
    }

    public void setInventoryTransferNumber(String inventoryTransferNumber) {
        this.inventoryTransferNumber = inventoryTransferNumber;
    }

    public Long getRecallDate() {
        return recallDate;
    }

    public void setRecallDate(Long recallDate) {
        this.recallDate = recallDate;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getPricingCode() {
        return pricingCode;
    }

    public void setPricingCode(String pricingCode) {
        this.pricingCode = pricingCode;
    }

    public String getPricingCycle() {
        return pricingCycle;
    }

    public void setPricingCycle(String pricingCycle) {
        this.pricingCycle = pricingCycle;
    }

    public Long getPricingBeginDate() {
        return pricingBeginDate;
    }

    public void setPricingBeginDate(Long pricingBeginDate) {
        this.pricingBeginDate = pricingBeginDate;
    }

    public Long getPricingEndDate() {
        return pricingEndDate;
    }

    public void setPricingEndDate(Long pricingEndDate) {
        this.pricingEndDate = pricingEndDate;
    }

    public Long getPricingPauseDate() {
        return pricingPauseDate;
    }

    public void setPricingPauseDate(Long pricingPauseDate) {
        this.pricingPauseDate = pricingPauseDate;
    }

    public Long getPricingChangeDate() {
        return pricingChangeDate;
    }

    public void setPricingChangeDate(Long pricingChangeDate) {
        this.pricingChangeDate = pricingChangeDate;
    }

    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public void setSubscriptionStatus(String subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public String getOriginContract() {
        return originContract;
    }

    public void setOriginContract(String originContract) {
        this.originContract = originContract;
    }

    public String getOriginPo() {
        return originPo;
    }

    public void setOriginPo(String originPo) {
        this.originPo = originPo;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public String getOriginAgency() {
        return originAgency;
    }

    public void setOriginAgency(String originAgency) {
        this.originAgency = originAgency;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccountingCode() {
        return accountingCode;
    }

    public void setAccountingCode(String accountingCode) {
        this.accountingCode = accountingCode;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }
}
