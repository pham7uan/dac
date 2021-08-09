package vn.seven.stc.core.personal;

import org.springframework.stereotype.Service;
import vn.seven.stc.core.CrudService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class DefaultConfigService extends CrudService<DefaultConfig,Long> {
    private DefaultConfigRepository defaultConfigRepository;

    public DefaultConfigService(DefaultConfigRepository defaultConfigRepository ) {
        this.repository=this.defaultConfigRepository = defaultConfigRepository;
    }

    public DefaultConfig findFirstByUserIdAndPageAndObjectIdAndActive(Long userId, String page,Long objectId,Boolean active){
        return defaultConfigRepository.findFirstByUserIdAndPageAndObjectIdAndActive(userId,page,objectId,active);
    }

    public DefaultConfig findFirstByUserIdAndPageAndObjectIdAndActiveAndConfigId(Long userId, String page,Long objectId,Boolean active,Long configId){
        return defaultConfigRepository.findFirstByUserIdAndPageAndObjectIdAndActiveAndConfigId(userId,page,objectId,active,configId);
    }

    public void deleteAllByConfigId(Long configId){
        defaultConfigRepository.deleteAllByConfigId(configId);
    }

    public Set<Long> getRemoveFavorites(Long userId, String page){
        Set<Long> ids = new HashSet<>();
        List<DefaultConfig> defaultConfigs = defaultConfigRepository.findAllByUserIdAndPageAndActive(userId,page,false);
        for(DefaultConfig dc:defaultConfigs){
            ids.add(dc.getConfigId());
        }
        return ids;
    }

}

