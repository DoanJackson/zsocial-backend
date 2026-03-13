package com.example.zsocial.backend.chat.dto.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class ConversationLoadRequest {
    private int size = 10;

    private String nextCursor;
}
