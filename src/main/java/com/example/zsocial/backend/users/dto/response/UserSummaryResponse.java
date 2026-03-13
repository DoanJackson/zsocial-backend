package com.example.zsocial.backend.users.dto.response;

import com.example.zsocial.backend.media.dto.response.MediaBaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSummaryResponse {
    Long userId;
    String username;
    String fullName;
    MediaBaseResponse avatar;
}
