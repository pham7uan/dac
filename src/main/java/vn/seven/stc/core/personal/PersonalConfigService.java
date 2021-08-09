package vn.seven.stc.core.personal;

import org.springframework.stereotype.Service;
import vn.seven.stc.core.CrudService;


import javax.transaction.Transactional;

@Service
@Transactional
public class PersonalConfigService extends CrudService<PersonalConfig,Long> {
//    private PersonalConfigRepository personalConfigRepository;
//    private AdvancedFilterService advancedFilterService;
//    private PropertyValueHelper propertyValueHelper;
//    private DefaultConfigService defaultConfigService;
//    private UserService userService;
//
//    @Autowired
//    public void setPropertyValueHelper(PropertyValueHelper propertyValueHelper) {
//        this.propertyValueHelper = propertyValueHelper;
//    }
//
//    @Autowired
//    public void setAdvancedFilterService(AdvancedFilterService advancedFilterService) {
//        this.advancedFilterService = advancedFilterService;
//    }
//
//    @Autowired
//    public void setDefaultConfigRepository(DefaultConfigService defaultConfigService) {
//        this.defaultConfigService = defaultConfigService;
//    }
//
//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }
//
//    public PersonalConfigService(PersonalConfigRepository personalConfigRepository) {
//        this.repository=this.personalConfigRepository = personalConfigRepository;
//    }
//
//    @Override
//    protected void afterCreate(PersonalConfig entity){
//        super.afterCreate(entity);
//        if(entity.getDefault()!=null && entity.getDefault()){
//            DefaultConfig dc = defaultConfigService.findFirstByUserIdAndPageAndObjectIdAndActive(entity.getUserId(),entity.getPage(),entity.getObjectId(),true);
//            if(dc !=null){
//                dc.setConfigId(entity.getId());
//                defaultConfigService.update(dc.getId(),dc);
//            } else {
//                DefaultConfig defaultConfig = new DefaultConfig();
//                defaultConfig.setEmail(entity.getEmail());
//                defaultConfig.setUserId(entity.getUserId());
//                defaultConfig.setPage(entity.getPage());
//                defaultConfig.setObjectId(entity.getObjectId());
//                defaultConfig.setConfigId(entity.getId());
//                defaultConfigService.create(defaultConfig);
//            }
//        }
//        int index = 0;
//        for(AdvancedFilterGroup group: entity.getAdvancedFilterGroups()){
//            for(AdvancedFilter af: group.getAdvancedFilters()){
//                if(af.getSelected() !=null && af.getSelected()){
//                    af.setId(null);
//                    af.setConfigId(entity.getId());
//                    af.setGroupId(index);
//                    af.setUserId(entity.getUserId());
//                    af.setPage(entity.getPage());
//                    af.setObjectId(entity.getObjectId());
//                    af.setSpecialFilter(group.getSpecialFilter());
//                    advancedFilterService.create(af);
//                }
//            }
//            index++;
//        }
//    }
//
//    @Override
//    protected void afterDelete(PersonalConfig entity){
//        super.afterDelete(entity);
//        defaultConfigService.deleteAllByConfigId(entity.getId());
//    }
//
//    public Map<String,String> getDefaultParams(Long userId,String page,Long objectId){
//        Map<String,String> result = new HashMap<>();
//        PersonalConfig personalConfig = getDefaultConfigs(userId,page, objectId);
//        if(personalConfig !=null){
//            result = advancedFilterService.parse(personalConfig.getAdvancedFilterGroups());
//        }
//        return result;
//    }
//
//    public PersonalConfig getDefaultConfigs(Long userId,String page,Long objectId){
//        DefaultConfig dc = defaultConfigService.findFirstByUserIdAndPageAndObjectIdAndActive(userId,page,objectId,true);
//        if(dc !=null){
//            PersonalConfig personalConfig =get(dc.getConfigId());
//            if(personalConfig !=null){
//                List<AdvancedFilterGroup> advancedFilterGroups = new ArrayList<>();
//                List<AdvancedFilter> advancedFilters = advancedFilterService.findByConfigId(personalConfig.getId());
//                Map<Integer,List<AdvancedFilter>> mapGroups = new HashMap<>();
//                for(AdvancedFilter advancedFilter: advancedFilters){
//                    if(advancedFilter.getSpecialFilter()){
//                        AdvancedFilterGroup advancedFilterGroup = new AdvancedFilterGroup();
//                        advancedFilterGroup.setSpecialFilter(true);
//                        List<AdvancedFilter> advancedFilterList = new ArrayList<>();
//                        advancedFilterList.add(advancedFilter);
//                        advancedFilterGroup.setAdvancedFilters(advancedFilterList);
//                        advancedFilterGroups.add(advancedFilterGroup);
//                    } else {
//                        advancedFilter.setSelected(true);
//                        if(mapGroups.containsKey(advancedFilter.getGroupId())){
//                            mapGroups.get(advancedFilter.getGroupId()).add(advancedFilter);
//                        } else {
//                            List<AdvancedFilter> advancedFilterList = new ArrayList<>();
//                            advancedFilterList.add(advancedFilter);
//                            mapGroups.put(advancedFilter.getGroupId(),advancedFilterList);
//                        }
//                    }
//                }
//                for (Map.Entry<Integer,List<AdvancedFilter>> entry : mapGroups.entrySet())
//                {
//                    AdvancedFilterGroup advancedFilterGroup = new AdvancedFilterGroup();
//                    advancedFilterGroup.setInFavorite(true);
//                    advancedFilterGroup.setSpecialFilter(false);
//                    advancedFilterGroup.setAdvancedFilters(entry.getValue());
//                    advancedFilterGroups.add(advancedFilterGroup);
//                }
//                personalConfig.setAdvancedFilterGroups(advancedFilterGroups);
//            }
//            return personalConfig;
//        }
//        return  null;
//    }
//
//    public void setDefault(Long id,Long userId,Boolean isDefault){
//        PersonalConfig pc = get(id);
//        DefaultConfig dc = defaultConfigService.findFirstByUserIdAndPageAndObjectIdAndActive(userId,pc.getPage(),pc.getObjectId(),true);
//        if(dc !=null && isDefault){
//            dc.setConfigId(id);
//            defaultConfigService.update(dc.getId(),dc);
//        } else if(isDefault) {
//            DefaultConfig defaultConfig = new DefaultConfig();
//            defaultConfig.setConfigId(pc.getId());
//            User user = userService.get(userId);
//            defaultConfig.setEmail(user.getEmail());
//            defaultConfig.setUserId(userId);
//            defaultConfig.setPage(pc.getPage());
//            defaultConfig.setObjectId(pc.getObjectId());
//            defaultConfigService.create(defaultConfig);
//        } else if(dc !=null) {
//            defaultConfigService.delete(dc);
//        }
//    }
//
//    public Map<String,String> getFkValue(String fieldName, String fk) throws InvocationTargetException, IllegalAccessException {
//        Long key = Long.parseLong(fk);
//        String fkValue = propertyValueHelper.getFkValue(fieldName,key).toString();
//        Map<String,String> result = new HashMap<>();
//        result.put("fkValue",fkValue);
//        return result;
//    }
//
//    public void removeFavorite(Long id, Long userId){
//        PersonalConfig pc = get(id);
//        if(pc !=null){
//            if(pc.getUserId().equals(userId)){
//                delete(pc);
//            } else {
//                DefaultConfig defaultConfig = defaultConfigService.findFirstByUserIdAndPageAndObjectIdAndActiveAndConfigId(userId,pc.getPage(),pc.getObjectId(),true,id);
//                if(defaultConfig !=null){
//                    defaultConfig.setActive(false);
//                    defaultConfigService.update(defaultConfig.getId(),defaultConfig);
//                } else {
//                    defaultConfig = new DefaultConfig();
//                    defaultConfig.setConfigId(pc.getId());
//                    User user = userService.get(userId);
//                    defaultConfig.setEmail(user.getEmail());
//                    defaultConfig.setUserId(userId);
//                    defaultConfig.setPage(pc.getPage());
//                    defaultConfig.setActive(false);
//                    defaultConfigService.create(defaultConfig);
//                }
//            }
//        }
//    }
}

