package vn.seven.stc.core.filterstorage;

import java.util.ArrayList;
import java.util.List;

public class AdvancedFilterGroup {
    private Boolean specialFilter;
    private Boolean inFavorite;
    private List<AdvancedFilter> advancedFilters = new ArrayList<>();

    public Boolean getSpecialFilter() {
        return specialFilter;
    }

    public void setSpecialFilter(Boolean specialFilter) {
        this.specialFilter = specialFilter;
    }

    public List<AdvancedFilter> getAdvancedFilters() {
        return advancedFilters;
    }

    public void setAdvancedFilters(List<AdvancedFilter> advancedFilters) {
        this.advancedFilters = advancedFilters;
    }

    public Boolean getInFavorite() {
        return inFavorite;
    }

    public void setInFavorite(Boolean inFavorite) {
        this.inFavorite = inFavorite;
    }
}
