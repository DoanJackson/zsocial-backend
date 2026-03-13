package com.example.zsocial.backend.infrastructure.websocket;

public interface SocketService {
    void sendToTopic(String destination, Object payload);
}
