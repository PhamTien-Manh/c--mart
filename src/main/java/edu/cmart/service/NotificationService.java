package edu.cmart.service;

import edu.cmart.model.dto.Notification;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {

    void sendNotification(Notification notification, Long driverId);
}
