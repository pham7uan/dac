package vn.seven.stc.report;

import java.util.Set;

public class SearchReportInfo {
    private Set<Integer> areaIds;
    private Set<Long> projectIds;
    private Set<Long> contractIds;
    private Integer hasActive;
    private Set<String> areaCodes;
    private Long activeStartDate;
    private Long activeEndDate;
    private String typeExport;

    public SearchReportInfo() {
    }

    public Integer getHasActive() {
        return hasActive;
    }

    public void setHasActive(Integer hasActive) {
        this.hasActive = hasActive;
    }

    public Set<Long> getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(Set<Long> projectIds) {
        this.projectIds = projectIds;
    }

    public Set<Long> getContractIds() {
        return contractIds;
    }

    public void setContractIds(Set<Long> contractIds) {
        this.contractIds = contractIds;
    }

    public String getTypeExport() {
        return typeExport;
    }

    public void setTypeExport(String typeExport) {
        this.typeExport = typeExport;
    }

    public Set<Integer> getAreaIds() {
        return areaIds;
    }

    public void setAreaIds(Set<Integer> areaIds) {
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