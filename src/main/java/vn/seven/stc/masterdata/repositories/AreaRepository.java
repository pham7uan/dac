package vn.seven.stc.masterdata.repositories;

import org.springframework.stereotype.Repository;
import vn.seven.stc.core.CustomJpaRepository;
import vn.seven.stc.masterdata.models.Area;

/**
 * Created by hiepnd
 * Date: 09/08/2021
 * Time: 2:49 PM
 * Contact me via mail hiepnd@vnpt-technology.vn
 */
@Repository
public interface AreaRepository extends CustomJpaRepository<Area, Long> {
}
