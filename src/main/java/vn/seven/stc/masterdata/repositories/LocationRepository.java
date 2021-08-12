package vn.seven.stc.masterdata.repositories;

import org.springframework.stereotype.Repository;
import vn.seven.stc.core.CustomJpaRepository;
import vn.seven.stc.masterdata.models.Location;

/**
 * Created by hiepnd
 * Date: 09/08/2021
 * Time: 2:50 PM
 * Contact me via mail hiepnd@vnpt-technology.vn
 */
@Repository
public interface LocationRepository extends CustomJpaRepository<Location, Long> {
    Boolean existsByCode(String locationCode);
}
