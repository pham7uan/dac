package vn.seven.stc.masterdata.endpoints;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.seven.stc.core.CrudApiEndpoint;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.masterdata.models.Area;
import vn.seven.stc.masterdata.service.AreaService;

/**
 * Created by hiepnd
 * Date: 09/08/2021
 * Time: 2:57 PM
 * Contact me via mail hiepnd@vnpt-technology.vn
 */
@RestController
@RequestMapping("/api/area")
public class AreaEndPoint extends CrudApiEndpoint<Area, Long> {

    private AreaService areaService;

    public AreaEndPoint(AreaService service) {
        super(service);
        this.areaService = service;
        this.baseUrl = "/api/area";
    }
}
