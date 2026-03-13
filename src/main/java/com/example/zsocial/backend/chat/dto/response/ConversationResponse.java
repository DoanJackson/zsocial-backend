package com.example.zsocial.backend.chat.dto.response;

import com.example.zsocial.backend.chat.model.enums.ConversationType;
import com.example.zsocial.backend.users.dto.response.UserSummaryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ConversationResponse {
    private Long id;
    private String avatar;
    private String groupName;
    private String lastMessageContent;
    private LocalDateTime lastMessageAt;
    private UserSummaryResponse lastMessageSender;
    private ConversationType type;
    private boolean isGroup;
}
