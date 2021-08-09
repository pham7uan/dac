package vn.seven.stc.core.processing;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.seven.stc.core.CustomJpaRepository;

import java.util.List;

public interface BaseProcessingRepository extends CustomJpaRepository<BaseProcessing,Long> {
    BaseProcessing findFirstByReference(String reference);
    BaseProcessing findFirstByObjectIdAndObjectType(Long objectId,String objectType);
    List<BaseProcessing> findAllByProcessingNot(Integer processing);
    List<BaseProcessing> findAllByObjectIdAndObjectType(Long id, String type);

    @Modifying
    @Query("UPDATE BaseProcessing c SET c.processing = 100")
    int finishAllProcess();

    @Modifying
    @Query("UPDATE BaseProcessing c SET c.processing = 100 WHERE c.id =:id")
    int finishProcess(@Param("id") Long id);
}
