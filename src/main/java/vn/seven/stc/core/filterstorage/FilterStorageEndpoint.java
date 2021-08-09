package vn.seven.stc.core.filterstorage;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.seven.stc.core.CrudApiEndpoint;

@RestController
@RequestMapping("/api/filters")
public class FilterStorageEndpoint extends CrudApiEndpoint<FilterStorage,Long> {
    private FilterStorageService filterService;
    public FilterStorageEndpoint(FilterStorageService service) {
        super(service);
        this.filterService = service;
        this.baseUrl = "/api/filters";
    }

    @RequestMapping(path = "/countByNameAndModel", method = RequestMethod.GET)
    public int countByNameAndModel(@RequestParam("name") String name,
                                 @RequestParam("model") String model) {
        return filterService.countByNameAndModel(name, model);
    }

    @RequestMapping(path = "/findByNameAndModel", method = RequestMethod.GET)
    public FilterStorage findByNameAndModel(@RequestParam("name") String name,
                                            @RequestParam("model") String model) {
        return filterService.findByNameAndModel(name, model);
    }
}
