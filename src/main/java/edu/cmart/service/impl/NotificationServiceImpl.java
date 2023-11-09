package edu.cmart.service.impl;

import edu.cmart.model.dto.Notification;
import edu.cmart.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void sendNotification(@Payload Notification notification, Long driverId) {
        notification.setContent("Have a new order!");
        notification.setSender("admin");
        messagingTemplate.convertAndSend("/topic/private/driver/" + driverId, notification);
    }
}
