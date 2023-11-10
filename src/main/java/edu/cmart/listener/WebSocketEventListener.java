package edu.cmart.listener;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    private final SimpMessageSendingOperations messagingTemplate;

//    @EventListener
//    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
//        logger.info("Received a new web socket connection");
//    }
//    @EventListener
//    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
//        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//
//        String username = (String) headerAccessor.getSessionAttributes().get("username");
//        if(username != null) {
//            logger.info("User Disconnected : " + username);
//
//            Message message = new Message();
//            message.setTypeMessage(TypeMessage.TEXT);
//            message.setContent("User " + username + " left the chat");
//
//            messagingTemplate.convertAndSend("/topic/public", chatMessage);
//        }
//    }
}
