package vn.seven.stc.core.filterstorage;

import org.springframework.web.bind.annotation.*;
import vn.seven.stc.core.CrudApiEndpoint;

@RestController
@RequestMapping("/api/advanced-filters")
public class AdvancedFilterEndpoint extends CrudApiEndpoint<AdvancedFilter,Long> {
    private AdvancedFilterService advancedFilterService;
    public AdvancedFilterEndpoint(AdvancedFilterService service) {
        super(service);
        this.advancedFilterService = service;
        this.baseUrl = "/api/advanced-filters";
    }
//    @RequestMapping(path = "/parse", method = RequestMethod.POST)
//    public Map<String,String> parse(@RequestBody List<AdvancedFilterGroup> advancedFilterGroups) {
//        return advancedFilterService.parse(advancedFilterGroups);
//    }
//
//    @RequestMapping(path = "/sum-quantity", method = RequestMethod.POST)
//    public List<BigDecimal> sumQuantity(@RequestParam("query") String query, @RequestParam("model") String model, @RequestBody String fields) {
//        return advancedFilterService.sumQuantity(query,model,fields);
//    }
//
//    @RequestMapping(path = "/groups", method = RequestMethod.POST)
//    public AdvancedFilterGroup createGroup(@RequestParam("userId") Long userId, @RequestParam("page") String page, @RequestParam(value = "objectId", required = false) Long objectId,@RequestBody AdvancedFilterGroup entity) {
//        return advancedFilterService.createGroup(userId,page,objectId,entity);
//    }
//
//    @RequestMapping(path = "/groups", method = RequestMethod.GET)
//    public List<AdvancedFilterGroup> createGroup(@RequestParam("userId") Long userId, @RequestParam("page") String page,@RequestParam(value = "objectId", required = false) Long objectId) {
//        return advancedFilterService.getGroups(userId,page,objectId);
//    }
}
