package vn.seven.stc.umgr.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import vn.seven.stc.umgr.models.Role;
import vn.seven.stc.core.CustomJpaRepository;

import java.util.Set;

/**
 * Created by quocvi3t on 11/13/17.c
 */
public interface RoleRepository extends CustomJpaRepository<Role,Long> {
    @EntityGraph(attributePaths = {"privileges","users"})
    Role findOneWithPrivilegesById(Long id);
    Role findFirstByName(String name);
    Page<Role> findAllByNameNotIn(Set<String> defaultRoles, Pageable pageable);

}

