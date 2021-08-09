package vn.seven.stc.core.processing;

import org.springframework.web.bind.annotation.*;
import vn.seven.stc.core.CrudApiEndpoint;

@RestController
@RequestMapping("/api/base-processing")
public class BaseProcessingEndpoint extends CrudApiEndpoint<BaseProcessing,Long> {
    private BaseProcessingService baseProcessingService;
    public BaseProcessingEndpoint(BaseProcessingService service) {
        super(service);
        this.baseProcessingService = service;
        this.baseUrl = "/api/base-processing";
    }

    @RequestMapping(path="/find-by-reference", method = RequestMethod.GET)
    public BaseProcessing filter(@RequestParam("reference") String reference) {
        return baseProcessingService.findByReference(reference);
    }

    @RequestMapping(path="/find-by-object", method = RequestMethod.GET)
    public BaseProcessing filterByObject(@RequestParam("id") Long id,@RequestParam("model") String model) {
        return baseProcessingService.findFirstByObjectIdAndObjectType(id,model);
    }

    @RequestMapping(path="/finish-process", method = RequestMethod.GET)
    public void finishProcess(@RequestParam("id") Long id) {
         baseProcessingService.finishProcess(id);
    }

}
