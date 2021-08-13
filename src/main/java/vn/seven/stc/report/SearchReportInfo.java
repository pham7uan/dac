package vn.seven.stc.report;

import java.util.Set;

public class SearchReportInfo {
    private Set<Long> areaIds;
    private Set<String> areaCodes;
    private Long activeStartDate;
    private Long activeEndDate;
    private String typeExport;

    public SearchReportInfo() {
    }

    public String getTypeExport() {
        return typeExport;
    }

    public void setTypeExport(String typeExport) {
        this.typeExport = typeExport;
    }

    public Set<Long> getAreaIds() {
        return areaIds;
    }

    public void setAreaIds(Set<Long> areaIds) {
        this.areaIds = areaIds;
    }

    public Set<String> getAreaCodes() {
        return areaCodes;
    }

    public void setAreaCodes(Set<String> areaCodes) {
        this.areaCodes = areaCodes;
    }

    public Long getActiveStartDate() {
        return activeStartDate;
    }

    public void setActiveStartDate(Long activeStartDate) {
        this.activeStartDate = activeStartDate;
    }

    public Long getActiveEndDate() {
        return activeEndDate;
    }

    public void setActiveEndDate(Long activeEndDate) {
        this.activeEndDate = activeEndDate;
    }
}