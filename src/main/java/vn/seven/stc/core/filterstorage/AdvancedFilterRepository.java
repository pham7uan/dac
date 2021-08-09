package vn.seven.stc.core.filterstorage;

import vn.seven.stc.core.CustomJpaRepository;

import java.util.List;

public interface AdvancedFilterRepository extends CustomJpaRepository<AdvancedFilter,Long> {
    List<AdvancedFilter> findAllByConfigId(Long configId);
    List<AdvancedFilter> findAllByUserIdAndPageAndObjectIdAndConfigId(Long userId,String page,Long objectId,Long configId);
    AdvancedFilter findFirstByUserIdAndPageAndObjectIdAndConfigIdOrderByGroupIdDesc(Long userId,String page,Long objectId,Long configId);
}
