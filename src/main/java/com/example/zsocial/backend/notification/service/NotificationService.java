package com.example.zsocial.backend.notification.service;

import com.example.zsocial.backend.chat.dto.response.ConversationSocketResponse;
import com.example.zsocial.backend.chat.dto.response.MessageRecalledPayload;
import com.example.zsocial.backend.chat.dto.response.MessageResponse;

public interface NotificationService {
    
    public void handleNewMessageNotification(MessageResponse message, ConversationSocketResponse conversation);
    
    public void handleMessageRecalledNotification(MessageRecalledPayload payload);
}
