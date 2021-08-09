package vn.seven.stc.umgr.repositories;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.seven.stc.umgr.models.User;
import vn.seven.stc.core.CustomJpaRepository;

import java.util.List;
import java.util.Set;

/**
 * Created by quocvi3t on 11/13/17.
 */
@Repository
public interface UserRepository extends CustomJpaRepository<User,Long> {
    @EntityGraph(attributePaths = {"roles"})
    User findOneWithRolesById(Long id);
    User findFirstByEmailIgnoreCase(String email);
    @EntityGraph(attributePaths = "roles")
    User findOneWithRolesByEmail(String email);
    List<User> findAllByIdIn(Set<Long> ids);

    @Modifying
    @Query(value = "UPDATE base_users SET jwt_token = NULL where id = ?1", nativeQuery = true)
    void clearJwtToken(Long id);
    User findFirstById(Long id);
    List<User> findAllByIdInAndActive(Set<Long> ids, Boolean active);
}
