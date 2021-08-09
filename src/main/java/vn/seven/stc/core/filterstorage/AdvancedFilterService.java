package vn.seven.stc.core.filterstorage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.seven.stc.core.CrudService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@Transactional
public class AdvancedFilterService   extends CrudService<AdvancedFilter,Long> {
    private static final Logger logger = LoggerFactory.getLogger(AdvancedFilterService.class);
    private AdvancedFilterRepository advancedFilterRepository;
//    private UserService userService;

    @PersistenceContext
    private EntityManager entityManager;

    public AdvancedFilterService(AdvancedFilterRepository advancedFilterRepository ) {
        this.repository = this.advancedFilterRepository = advancedFilterRepository;
    }
//
//
//    public AdvancedFilter createOrUpdate(AdvancedFilter entity){
//        if(entity.getId() !=null){
//            update(entity.getId(),entity);
//        } else {
//            create(entity);
//        }
//        return entity;
//    }
//
//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }
//
//    public Map<String,String> parse(List<AdvancedFilterGroup>  advancedFilterGroups){
//        String rSql = "";
//        Boolean notFound = false;
//        // ===========simple special filter============
//        String specialFilters = "";
//        for(AdvancedFilterGroup advancedFilterGroup: advancedFilterGroups){
//            if(advancedFilterGroup.getSpecialFilter()){
//                List<AdvancedFilter> advancedFilters = advancedFilterGroup.getAdvancedFilters();
//                specialFilters += specialFilters.isEmpty()?advancedFilters.get(0).getValue():(";" + advancedFilters.get(0).getValue());
//            }
//        }
//        if(!specialFilters.isEmpty()){
//            specialFilters = "(" + specialFilters + ")";
//            rSql = specialFilters;
//        }
//
//        // =============custom filter==================
//        String customFilters = "";
//        for(AdvancedFilterGroup advancedFilterGroup: advancedFilterGroups){
//            if(advancedFilterGroup.getSpecialFilter()){
//                continue;
//            }
//            String groupFilters = "";
//            List<AdvancedFilter> advancedFilters = advancedFilterGroup.getAdvancedFilters();
//            for(AdvancedFilter af:advancedFilters){
//                if(af.getSelected() !=null && af.getSelected()){
////                    af.setValue(af.getValue().replaceAll(" ","%20"));
//                    if("text".equals(af.getType())){
//                        String query = parseOperatorForText(af.getField(),af.getOperator(), af.getValue());
//                        groupFilters += groupFilters.isEmpty()?query:(","+query);
//                    } else if ("number".equals(af.getType())){
//                        String query = parseOperator(af.getField(),af.getOperator(), af.getValue());
//                        groupFilters += groupFilters.isEmpty()?query:(","+query);
//                    } else if ("id".equals(af.getType())){
//                        if(af.getValue() !=null && !af.getValue().equals("null") && !"isNull".equals(af.getOperator()) && !"isNotNull".equals(af.getOperator())){
//                            String relateQuery = parseOperatorForText(af.getOriginalField(),af.getOperator(), af.getValue());
//                            String fkValues = getRelateObjects(af.getModel(),relateQuery);
//                            if(fkValues !=null){
//                                if(af.getOperator().contains("!") || af.getOperator().contains("out")){
//                                    String query = "(" + af.getField() + "=in=" + fkValues +"," + af.getField() +"==null)";
//                                    groupFilters += groupFilters.isEmpty()?query:(","+query);
//                                } else {
//                                    String query = af.getField() + "=in=" + fkValues;
//                                    groupFilters += groupFilters.isEmpty()?query:(","+query);
//                                }
//
//                            } else {
//                                notFound = true;
//                            }
//                        } else {
//                            String query = parseOperator(af.getField(),af.getOperator(), af.getValue());
//                            groupFilters += groupFilters.isEmpty()?query:(","+query);
//                        }
//
//                    } else if("date".equals(af.getType())){
//                        if(af.getValue() !=null && !af.getValue().equals("null") && !"isNull".equals(af.getOperator()) && !"isNotNull".equals(af.getOperator())){
//                            String query = getDateQuery(af.getField(), af.getOperator(), af.getValue());
//                            if(query !=null && !query.isEmpty()){
//                                groupFilters += groupFilters.isEmpty()?query:(","+query);
//                            } else {
//                                notFound = true;
//                            }
//                        } else {
//                            String query = parseOperator(af.getField(),af.getOperator(), af.getValue());
//                            groupFilters += groupFilters.isEmpty()?query:(","+query);
//                        }
//
//                    } else if("special".equals(af.getType())){
//                        if(!af.getValue().equals("null")){
//                            if("internalReference".equals(af.getOriginalField())){
//                                String relateQuery = parseOperator(af.getOriginalField(),af.getOperator(), af.getValue());
//                                String fkValues = getRelateObjects(af.getModel(),relateQuery);
//                                if(!fkValues.isEmpty()){
//                                    groupFilters += groupFilters.isEmpty()?fkValues:(","+fkValues);
//                                } else {
//                                    notFound = true;
//                                }
//                            }
//                        }else {
//                            String query = parseOperator(af.getField(),af.getOperator(), af.getValue());
//                            groupFilters += groupFilters.isEmpty()?query:(","+query);
//                        }
//
//                    }
//                }
//            }
//            if(!groupFilters.isEmpty()){
//                groupFilters = "(" + groupFilters + ")";
//                customFilters += customFilters.isEmpty()?groupFilters:(";"+groupFilters);
//            }
//        }
//        if(!customFilters.isEmpty()){
//            rSql = rSql.isEmpty()?customFilters:(rSql + ";" + customFilters);
//        }
//        if(rSql.isEmpty() && notFound){
//            rSql="id<0";
//        }
//        Map<String,String> result = new HashedMap<>();
//        result.put("rSql",rSql);
//        return result;
//    }
//
//    private String parseOperatorForText(String field,String operator,String value) {
//        String  rSql = "";
//        switch (operator){
//            case "isNull":
//                rSql= field+"==null";    //null
//                break;
//            case "isNotNull":
//                rSql= field+"!=null";    //not null
//                break;
//            case "*==":
//                rSql= field+"==\'*"+value + "\'";    //begin with
//                break;
//            case "==*":
//                rSql= field+"==\'"+value +"*\'"; //end with
//                break;
//            case "==**":
//                rSql= field+"==\'*"+value +"*\'"; //contain
//                break;
//            case "!=**":
//                rSql="("+ field+"!=\'*"+value +"*\',"+field+"==null)"; //not contain
//                break;
//            case "=in=":
//                rSql= field+"=in=("+value +")"; //in list
//                break;
//            case "=out=":
//                rSql="("+ field+"=out=("+value +"),"+field+"==null)"; //not in list
//                break;
//            case "!=":
//                if(!value.equals("null")){
//                    rSql= "("+ field+"!=\'"+value +"\',"+field+"==null)"; //not equal to
//                } else{
//                    rSql= field + operator + "\'" + value + "\'";
//                }
//                break;
//            default:
//                rSql= field + operator + "\'" + value + "\'";
//                break;
//        }
//        return rSql;
//    }
//
//    private String parseOperator(String field,String operator,String value) {
//        String  rSql = "";
//        switch (operator){
//            case "isNull":
//                rSql= field+"==null";    //null
//                break;
//            case "isNotNull":
//                rSql= field+"!=null";    //not null
//                break;
//            case "*==":
//                rSql= field+"==*"+value;    //begin with
//                break;
//            case "==*":
//                rSql= field+"=="+value +"*"; //end with
//                break;
//            case "==**":
//                rSql= field+"==*"+value +"*"; //contain
//                break;
//            case "!=**":
//                rSql="("+ field+"!=*"+value +"*,"+field+"==null)"; //not contain
//                break;
//            case "=in=":
//                rSql= field+"=in=("+value +")"; //in list
//                break;
//            case "=out=":
//                rSql="("+ field+"=out=("+value +"),"+field+"==null)"; //not in list
//                break;
//            case "!=":
//                if(!value.equals("null")){
//                    rSql="("+ field+"=out=("+value +"),"+field+"==null)"; //not equal to
//                }  else{
//                    rSql= field + operator + value;
//                }
//                break;
//            default:
//                rSql= field + operator + value;
//                break;
//        }
//        return rSql;
//    }
//
//    private String getRelateObjects(String model, String query){
//        Set<Long> ids = new HashSet<>();
//        String result = null;
//        switch (model){
//            case "User":
//                List<User> users = userService.search(query);
//                for(User o: users){ ids.add(o.getId()); }
//                break;
//
//        }
//        if(ids.size() > 0){
//            result =  ids.toString().replaceAll("\\[","(").replaceAll("]",")").replaceAll(" ","");
//        }
//        return result;
//    }
//
//    private String getDateQuery(String field, String operator, String value){
//        String dateQuery = "";
//        try {
//            Long beginOfDate = Common.convertDateTime(value,"dd-MM-yyyy",0);
//            Long endOfDate = Common.convertDateTime(value,"dd-MM-yyyy",1);
//            if(operator.equals("==")){
//                dateQuery = "(" + field + ">=" + beginOfDate.toString() + ";" + field + "<" + endOfDate + ")";
//            } else {
//                dateQuery = field + operator + beginOfDate;
//            }
//        } catch (Exception e){
//            dateQuery = null;
//        }
//        return dateQuery;
//    }
//
//    public List<AdvancedFilter> findByConfigId(Long configId){
//        return advancedFilterRepository.findAllByConfigId(configId);
//    }
//
//    public List<BigDecimal> sumQuantity(String rSql, String model, String fields){
//        List<BigDecimal> quantity = getQuantities(rSql,model,fields);
//        return quantity;
//    }
//
//    public List<BigDecimal> getQuantities(String rSql, String model, String fields){
//        String sql ="";
//        if (!fields.isEmpty()){
//            sql = "SELECT " + fields + "  FROM " + model + " WHERE 1 =1 ";
//            String condition = Common.convertRsqlToSql(rSql);
//            if(condition !=null && !condition.isEmpty()){
//                sql += " AND " + condition;
//            }
//            try{
//                Query query = entityManager.createQuery(sql);
//                return query.getResultList();
//            } catch (Exception e){
//                logger.error(sql);
//                logger.error("Exception occurs: ",e);
//            }
//
//        }
//        return null;
//    }
//
//    public AdvancedFilterGroup createGroup(Long userId,String page,Long objectId,AdvancedFilterGroup entity){
//        int groupIndex = 0;
//        AdvancedFilter ad = advancedFilterRepository.findFirstByUserIdAndPageAndObjectIdAndConfigIdOrderByGroupIdDesc(userId,page,objectId,null);
//        if(ad !=null){
//            if(ad.getGroupId() != null){
//                groupIndex = ad.getGroupId() + 1;
//            }
//        }
//        for(AdvancedFilter advancedFilter:entity.getAdvancedFilters()){
//            advancedFilter.setGroupId(groupIndex);
//            advancedFilter.setObjectId(objectId);
//            advancedFilter.setUserId(userId);
//            advancedFilter.setPage(page);
//            create(advancedFilter);
//        }
//        return  entity;
//    }
//
//    public List<AdvancedFilterGroup> getGroups(Long userId, String page,Long objectId){
//        List<AdvancedFilterGroup> result = new LinkedList<>();
//        List<AdvancedFilter> advancedFilters = advancedFilterRepository.findAllByUserIdAndPageAndObjectIdAndConfigId(userId,page,objectId,null);
//        Map<Integer,List<AdvancedFilter>> mapGroup = new HashedMap<>();
//        for(AdvancedFilter af:advancedFilters){
//            if(af.getGroupId() !=null){
//                if(mapGroup.containsKey(af.getGroupId())){
//                    mapGroup.get(af.getGroupId()).add(af);
//                } else {
//                    List<AdvancedFilter> afByGroup = new LinkedList<>();
//                    afByGroup.add(af);
//                    mapGroup.put(af.getGroupId(),afByGroup);
//                }
//            }
//        }
//        for (Map.Entry<Integer, List<AdvancedFilter>> entry : mapGroup.entrySet())
//        {
//            AdvancedFilterGroup group = new AdvancedFilterGroup();
//            group.setSpecialFilter(false);
//            group.setAdvancedFilters(entry.getValue());
//            result.add(group);
//        }
//        return result;
//    }
}

