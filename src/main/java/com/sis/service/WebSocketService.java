package com.sis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sis.util.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebSocketService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper;

    public void sendUploadDoneNotification(Integer status, String message) {
        try {
            messagingTemplate.convertAndSend("/topic/uploadedUsers", objectMapper.writeValueAsString(new MessageResponse(status, message, null)));
        } catch (JsonProcessingException e) {
            logger.error("error opining file", e);
        }
    }
}
