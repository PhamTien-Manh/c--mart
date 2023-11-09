package edu.cmart.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static edu.cmart.util.api.ConstantsApi.Message.MESSAGE_PATH;

@RestController
@RequiredArgsConstructor
@RequestMapping(MESSAGE_PATH)
@Tag(name = "MessageController", description = "Functionality for Message")
public class MessageController {

    private final SimpMessagingTemplate messagingTemplate;

//    @GetMapping("/send-notification")
//    public void sendNotification() {
//        messagingTemplate.convertAndSend("/topic/public", "Hello, this is a notification!");
//    }
}
