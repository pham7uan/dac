package vn.seven.stc.logs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import vn.seven.stc.umgr.models.User;
import vn.seven.stc.umgr.services.UserService;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
public class PropertyValueHelper {
    @Autowired
    private ApplicationContext context;

    private Map<String,Field> FIELDS = new HashMap<>();
    private Map<String,Class<?>> SERVICES = new HashMap<>();

    @Autowired
    public void setServices() {
                this.SERVICES.put(User.class.getName(),UserService.class);
                    }

    @Autowired
    public void setHelpers() throws NoSuchFieldException {
        this.FIELDS.put("ownerId",User.class.getDeclaredField("email"));
        this.FIELDS.put("assigneeId",User.class.getDeclaredField("email"));
        this.FIELDS.put("requesterId",User.class.getDeclaredField("email"));
    }


    public Object getFkValue(String propertyName, Object propertyValue) throws InvocationTargetException, IllegalAccessException {
        Field field = FIELDS.get(propertyName);
        if(field == null || propertyValue == null) {
            return propertyValue;
        }
        Object value = propertyValue;
        Class<?> serviceClazz = SERVICES.get(field.getDeclaringClass().getName());
        Object serviceInstance = context.getBean(serviceClazz);

        Method[] methods = serviceClazz.getMethods();
        for(Method m : methods) {
            if("get".equals(m.getName())) {
                Object entity = m.invoke(serviceInstance,propertyValue);
                field.setAccessible(true);
                value = field.get(entity);
                break;
            }
        }
        return value;
    }

}
