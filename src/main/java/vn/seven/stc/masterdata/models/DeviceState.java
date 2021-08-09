package vn.seven.stc.masterdata.models;

public class DeviceState {
    public final static Integer UNDEFINED = 0;              // Chưa xác định
    public final static Integer TECH_EXPORT = 1;            // Xuất kho VNPT Tech
    public final static Integer PROVINCE_IMPORT = 2;        // Nhập kho VTT
    public final static Integer INTERNAL_TRANSFER = 3;      // Xuất điều chuyển kho
    public final static Integer DEPLOYMENT_EXPORT = 4;      // Xuất đi lắp đặt
    public final static Integer DEPLOYED = 5;               // Đã lắp đặt cho KH
    public final static Integer RECALL = 6;                 // Thiết bị thu hồi
    public final static Integer TECH_RETURN = 7;            // Thiết bị gửi trả VNPT Tech
    public final static Integer GUARANTEE_COMPLETE = 8;     // Thiết bị đã bảo hành xong
    public final static Integer REUSED = 9;                 // Thiết bị tái sử dụng
}
