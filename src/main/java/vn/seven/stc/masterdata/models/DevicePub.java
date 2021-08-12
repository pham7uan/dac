package vn.seven.stc.masterdata.models;

public class DevicePub  {
    private String serial;
    private Integer state = DeviceState.EXPORTED;  //trạng thái thiết bị
    private Long areaId;                            // khu vực
    private String areaName;
    private Long deliveryDate;                      // ngày giao hàng
    private Long expiredDate;                       // ngày hết hạn bảo hành
    private Long recallDate;                        // ngày thu hồi thiết bị
    private String customerCode;                    // Mã khách hàng
    private String pricingCode;                     // gói cước
    private String pricingCycle;                    // Chu kỳ cước
    private Long pricingBeginDate;                  // Ngày bắt đầu gói cước
    private Long pricingEndDate;                    // Ngày thanh lý gói cước
    private Long pricingPauseDate;                  // Ngày tạm ngưng gói cước
    private Long pricingChangeDate;                 // Ngày đôi gói cước (rã gói)
    private String subscriptionStatus;              // Trạng thái thuê bao
    private String originAgency;                    // Đại lý gốc
    private String agency;                          // Đại lý hiện tại
    private String locationCode;                    // Mã kho
    private String locationName;                    // Tên kho
    private Integer customerType = 1;               //1 Khách hàng hiện hữu/ 2 khách hàng mới

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
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

    public Long getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Long deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Long getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Long expiredDate) {
        this.expiredDate = expiredDate;
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

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }
}
