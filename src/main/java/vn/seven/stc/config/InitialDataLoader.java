package vn.seven.stc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import vn.seven.stc.core.utils.Common;

import javax.transaction.Transactional;


@Component
public class InitialDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    private static final Logger log = LoggerFactory.getLogger(InitialDataLoader.class);

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        createDirectory();
    }

    private void createDirectory(){
        String resource =System.getProperty("user.dir");
        Common.createDirectory(resource +"/export/");
        Common.createDirectory(resource +"/attachment/");
        Common.createDirectory(resource +"/attachment/images/");
        Common.createDirectory(resource +"/attachment/transfer/");
        Common.createDirectory(resource +"/attachment/uploads/");
        Common.createDirectory(resource +"/attachment/uploads/");
        Common.createDirectory(resource +"/export/device");
    }

}
