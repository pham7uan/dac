package vn.seven.stc.umgr.repositories;

import vn.seven.stc.umgr.models.Privilege;
import vn.seven.stc.core.CustomJpaRepository;

/**
 * Created by quocvi3t on 11/13/17.
 */
public interface PrivilegeRepository extends CustomJpaRepository<Privilege,Long> {
    Privilege findFirstByName(String name);
}
