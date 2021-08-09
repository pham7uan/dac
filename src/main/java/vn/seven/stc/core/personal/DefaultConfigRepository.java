package vn.seven.stc.core.personal;

import vn.seven.stc.core.CustomJpaRepository;

import java.util.List;

public interface DefaultConfigRepository extends CustomJpaRepository<DefaultConfig,Long> {
    DefaultConfig findFirstByUserIdAndPageAndObjectIdAndActiveAndConfigId(Long userId, String page,Long objectId,Boolean active,Long configId);
    DefaultConfig findFirstByUserIdAndPageAndObjectIdAndActive(Long userId, String page,Long objectId,Boolean active);
    List<DefaultConfig> findAllByUserIdAndPageAndActive(Long userId, String page, Boolean active);
    Integer deleteAllByConfigId(Long configId);

}
