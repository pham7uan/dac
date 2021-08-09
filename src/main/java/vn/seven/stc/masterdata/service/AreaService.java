package vn.seven.stc.masterdata.service;

import org.springframework.stereotype.Service;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.masterdata.models.Area;
import vn.seven.stc.masterdata.repositories.AreaRepository;

/**
 * Created by hiepnd
 * Date: 09/08/2021
 * Time: 2:51 PM
 * Contact me via mail hiepnd@vnpt-technology.vn
 */
@Service
public class AreaService extends CrudService<Area, Long> {

    private AreaRepository areaRepository;

    public AreaService(AreaRepository repository) {
        this.repository = this.areaRepository = repository;
    }
}
