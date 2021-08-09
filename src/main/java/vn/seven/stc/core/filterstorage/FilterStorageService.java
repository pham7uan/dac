package vn.seven.stc.core.filterstorage;

import org.springframework.stereotype.Service;
import vn.seven.stc.core.CrudService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FilterStorageService extends CrudService<FilterStorage,Long> {
    private FilterStorageRepository filterRepository;
    public FilterStorageService(FilterStorageRepository repository) {
        this.repository = this.filterRepository = repository;
    }

    public int countByNameAndModel(String name, String model) {
        return filterRepository.countAllByNameAndModel(name, model);
    }

    public FilterStorage findByNameAndModel(String name, String model) {
        return filterRepository.findFirstByNameAndModel(name, model);
    }

    public boolean checkHasOperationTypeId(Long id, String model) {
        List<FilterStorage> filterStorages = filterRepository.findAllByModel(model);
        for (FilterStorage filter : filterStorages) {
            Map<String,Object> filterMap = filter.getFilter();
            if(filterMap.containsKey(model)) {
                Map<String, Object> maps = (Map<String, Object>) filterMap.get(model);
                if(maps.containsKey("operationTypeId")) {
                    List<Object> ids = (List<Object>) maps.get("operationTypeId");
                    for (Object temp : ids) {
                        if(temp instanceof Long) {
                            if(temp == id)
                                return true;
                        } else if(temp instanceof String) {
                            if(Long.parseLong(String.valueOf(temp)) == id)
                                return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}

