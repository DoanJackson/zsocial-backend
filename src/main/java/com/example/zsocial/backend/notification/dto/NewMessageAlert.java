package com.example.zsocial.backend.notification.dto;

import com.example.zsocial.backend.chat.dto.response.MessageResponse;
import com.example.zsocial.backend.chat.model.enums.ConversationType;
import com.example.zsocial.backend.media.dto.response.MediaBaseResponse;
import com.example.zsocial.backend.notification.enums.NotificationType;

import com.example.zsocial.backend.users.dto.response.UserSummaryResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class NewMessageAlert {
    private Long id;
    private Long conversationId;
    private String content;
    private UserSummaryResponse sender;
    private List<MediaBaseResponse> medias;
    private LocalDateTime createdAt;
    private String groupName;
    private String avatar;
    private ConversationType type;
    private boolean isGroup;
}
