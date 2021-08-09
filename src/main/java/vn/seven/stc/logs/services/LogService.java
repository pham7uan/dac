package vn.seven.stc.logs.services;

import org.javers.core.Javers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    private static final Logger log = LoggerFactory.getLogger(LogService.class);
    private Javers javers;

    @Autowired
    public void setJavers(Javers javers) {
        this.javers = javers;
    }

//    public List<Change> test() {
//        JqlQuery query = QueryBuilder.byClass(Transfer.class).build();
//        List<Change> changes = javers.findChanges(query);
//
//        for(Change change : changes) {
//            ValueChange valueChange = (ValueChange)change;
//            log.info("{}: {} -> {}",valueChange.getPropertyName(),valueChange.getLeft(),valueChange.getRight());
//        }
//        return changes;
//    }
}
