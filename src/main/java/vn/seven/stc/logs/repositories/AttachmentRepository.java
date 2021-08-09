package vn.seven.stc.logs.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.seven.stc.logs.models.Attachment;
import vn.seven.stc.core.CustomJpaRepository;

import java.util.List;

public interface AttachmentRepository extends CustomJpaRepository<Attachment,Long> {
    List<Attachment> findAllByParentId(Long parentId);
    List<Attachment> findAllByParentIdAndModel(Long parentId,String model);
    int deleteAllByParentIdAndModel(Long parentId,String model);

    @Query(value = "Select url  from base_attachments " +
            "where parent_id =:parentId " +
            " and model =:model " +
            " and type =:type ", nativeQuery = true)
    List<String> getByParentIdAndModelAndType(@Param("parentId") Long parentId,
                               @Param("model") String model,
                               @Param("type") Integer type);

    int deleteByUrl(String url);
}
