package vn.seven.stc.core.personal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.seven.stc.core.CrudApiEndpoint;

import java.util.Set;

@RestController
@RequestMapping("/api/default-configs")
public class DefaultConfigEndpoint extends CrudApiEndpoint<DefaultConfig,Long> {
    private DefaultConfigService defaultConfigService;
    public DefaultConfigEndpoint(DefaultConfigService service) {
        super(service);
        this.defaultConfigService = service;
        this.baseUrl = "/api/default-configs";
    }

    @RequestMapping(path = "/get-remove-favorites", method = RequestMethod.GET)
    public Set<Long> getDefaultParams(@RequestParam("userId") Long userId, @RequestParam ("page") String page) {
        return defaultConfigService.getRemoveFavorites(userId,page);
    }
}
