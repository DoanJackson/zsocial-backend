package com.example.zsocial.backend.users.dto.response;

import com.example.zsocial.backend.users.model.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponse {

    private String token;

    private Long userId;
    private RoleType role;
    private String fullName;
    private String avatar;
}
