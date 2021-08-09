package vn.seven.stc.logs.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.logs.models.Notification;
import vn.seven.stc.logs.repositories.NotificationRepository;

@Service
public class NotificationService extends CrudService<Notification,Long> {
    @Value("${baseUrl}")
    private String baseUrl;
    private NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.repository = this.notificationRepository = notificationRepository;
    }

    public void done(Long id, Boolean done){
        Notification notification = notificationRepository.findOne(id);
        if(id !=null){

        }
    }
}
