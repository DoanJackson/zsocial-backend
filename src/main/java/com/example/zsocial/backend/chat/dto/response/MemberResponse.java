package com.example.zsocial.backend.chat.dto.response;

import com.example.zsocial.backend.chat.model.enums.ConversationRole;
import com.example.zsocial.backend.users.dto.response.UserSummaryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {
    private UserSummaryResponse user;
    private ConversationRole role;
}
