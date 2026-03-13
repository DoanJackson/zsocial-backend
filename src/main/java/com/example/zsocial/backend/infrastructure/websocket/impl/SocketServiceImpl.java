package com.example.zsocial.backend.infrastructure.websocket.impl;

import com.example.zsocial.backend.chat.dto.response.MessageResponse;
import com.example.zsocial.backend.infrastructure.websocket.SocketService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SocketServiceImpl implements SocketService {

    private final SimpMessageSendingOperations messagingSendingOperations;

    @Override
    public void sendToTopic(String destination, Object payload) {
        try {
            messagingSendingOperations.convertAndSend(destination, payload);
            log.info("Sent to topic {}", destination);
        } catch (Exception e) {
            log.error("Failed to send to topic", e);
        }
    }

//    /**
//     * Gửi sự kiện Typing (Đang gõ...)
//     */
//    public void sendTypingEvent(Long conversationId, TypingEvent event) {
//        String destination = "/topic/conversation/" + conversationId;
//        messagingTemplate.convertAndSend(destination, event);
//    }
//
//    /**
//     * (Mở rộng sau này) Gửi thông báo riêng cho 1 user (Ví dụ: Có người kết bạn)
//     */
//    public void sendPrivateNotification(Long userId, NotificationResponse notification) {
//        // Destination chuẩn của Spring User Destination: /user/{userId}/queue/notifications
//        messagingTemplate.convertAndSendToUser(
//                String.valueOf(userId),
//                "/queue/notifications",
//                notification
//        );
//    }
}
