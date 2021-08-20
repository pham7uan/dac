package vn.seven.stc.report;

import vn.seven.stc.masterdata.models.Device;

import java.util.List;

public class DeviceReport {
    private Integer totalDeviceImport;
    private Integer totalHasNotDeviceActive;
    private Integer totalHasDeviceActive;
    private Integer totalHasContract;
    private List<Device> deviceImports;

    public DeviceReport() {
    }

    public Integer getTotalDeviceImport() {
        return totalDeviceImport;
    }

    public void setTotalDeviceImport(Integer totalDeviceImport) {
        this.totalDeviceImport = totalDeviceImport;
    }

    public Integer getTotalHasNotDeviceActive() {
        return totalHasNotDeviceActive;
    }

    public void setTotalHasNotDeviceActive(Integer totalHasNotDeviceActive) {
        this.totalHasNotDeviceActive = totalHasNotDeviceActive;
    }

    public Integer getTotalHasDeviceActive() {
        return totalHasDeviceActive;
    }

    public void setTotalHasDeviceActive(Integer totalHasDeviceActive) {
        this.totalHasDeviceActive = totalHasDeviceActive;
    }

    public Integer getTotalHasContract() {
        return totalHasContract;
    }

    public void setTotalHasContract(Integer totalHasContract) {
        this.totalHasContract = totalHasContract;
    }

    public List<Device> getDeviceImports() {
        return deviceImports;
    }

    public void setDeviceImports(List<Device> deviceImports) {
        this.deviceImports = deviceImports;
    }
}