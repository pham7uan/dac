package vn.seven.stc.logs.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.seven.stc.logs.models.AuditLog;
import vn.seven.stc.core.CustomJpaRepository;

import java.util.List;
import java.util.Set;

public interface AuditLogRepository extends CustomJpaRepository<AuditLog,Long> {
    List<AuditLog> findAllByParentIdAndRequestIdOrderByCreatedDesc(Long parentId, String requestId);
    AuditLog findFirstByObjectIdAndObjectTypeAndRequestId(Long objectId, String objectType, String requestId);
    AuditLog findFirstByParentIdAndObjectTypeAndRequestId(Long parentId, String objectType, String requestId);
    List<AuditLog> findAllByParentIdAndObjectTypeAndActionTypeAndRequestId(Long parentId, String objectType,String ActionType, String requestId);
    List<AuditLog> findAllByParentIdAndObjectTypeAndActionTypeAndObjectIdNotInOrderByCreatedAsc(Long parentId, String objectType, String actionType,Set<Long> objectIds);
    List<AuditLog> findAllByParentIdAndObjectTypeAndActionTypeOrderByCreatedAsc(Long parentId, String objectType, String actionType);

    @Query("SELECT DISTINCT c.objectId FROM AuditLog c WHERE c.parentId  =:parentId AND c.objectType  =:objectType AND c.actionType =:actionType")
    Set<Long> findObjectIds(@Param("parentId") Long parentId, @Param("objectType") String objectType, @Param("actionType") String actionType);
    List<AuditLog> findAllByObjectType(String objectType);
}
