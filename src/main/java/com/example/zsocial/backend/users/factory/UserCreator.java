package com.example.zsocial.backend.users.factory;

import com.example.zsocial.backend.users.dto.request.UserRegisterRequest;
import com.example.zsocial.backend.users.model.User;
import com.example.zsocial.backend.users.model.enums.RoleType;

public interface UserCreator {
    User create(UserRegisterRequest request);

    RoleType getRole();
}
