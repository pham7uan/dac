package vn.seven.stc.masterdata.models;

public class DevicePub  {
    private String serial;
    private Integer state = DeviceState.UNDEFINED;  //trạng thái thiết bị
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

}
