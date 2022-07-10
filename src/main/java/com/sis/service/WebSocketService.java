package com.sis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public void sendUploadDoneNotification() {
        messagingTemplate.convertAndSend("/topic/upload", "File processing is done");
    }
}
