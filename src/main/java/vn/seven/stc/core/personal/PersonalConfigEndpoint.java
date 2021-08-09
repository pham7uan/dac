package vn.seven.stc.core.personal;

import org.springframework.web.bind.annotation.*;
import vn.seven.stc.core.CrudApiEndpoint;

@RestController
@RequestMapping("/api/personal-configs")
public class PersonalConfigEndpoint extends CrudApiEndpoint<PersonalConfig,Long> {
    private PersonalConfigService personalConfigService;
    public PersonalConfigEndpoint(PersonalConfigService service) {
        super(service);
        this.personalConfigService = service;
        this.baseUrl = "/api/personal-configs";
    }

//    @RequestMapping(path = "/get-default-params", method = RequestMethod.GET)
//    public Map<String,String> getDefaultParams(@RequestParam ("userId") Long userId, @RequestParam ("page") String page,@RequestParam(value = "objectId", required = false) Long objectId) {
//        return personalConfigService.getDefaultParams(userId,page,objectId);
//    }
//
//    @RequestMapping(path = "/get-default-configs", method = RequestMethod.GET)
//    public PersonalConfig getDefaultConfigs(@RequestParam ("userId") Long userId, @RequestParam ("page") String page,@RequestParam(value = "objectId", required = false) Long objectId) {
//        return personalConfigService.getDefaultConfigs(userId,page,objectId);
//    }
//
//    @RequestMapping(path = "/set-default", method = RequestMethod.GET)
//    public void getDefaultConfigs(@RequestParam ("id") Long id,@RequestParam ("userId") Long userId,@RequestParam ("isDefault") Boolean isDefault) {
//         personalConfigService.setDefault(id,userId,isDefault);
//    }
//
//    @RequestMapping(path = "/get-fk-value", method = RequestMethod.GET)
//    public Map<String,String> getFkValue(@RequestParam ("fieldName") String fieldName, @RequestParam ("fk") String fk) throws InvocationTargetException, IllegalAccessException {
//        return personalConfigService.getFkValue(fieldName,fk);
//    }
//
//    @RequestMapping(path = "/remove", method = RequestMethod.GET)
//    public void removeFavorite(@RequestParam ("id") Long id,@RequestParam ("userId") Long userId) {
//        personalConfigService.removeFavorite(id,userId);
//    }
}
