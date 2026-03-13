package com.example.zsocial.backend.users.factory.creator;

import com.example.zsocial.backend.users.dto.request.UserRegisterRequest;
import com.example.zsocial.backend.users.factory.UserCreator;
import com.example.zsocial.backend.users.mapper.UserMapper;
import com.example.zsocial.backend.users.model.User;
import com.example.zsocial.backend.users.model.enums.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GuestCreator implements UserCreator {

    private final UserMapper userMapper;

    @Override
    public User create(UserRegisterRequest request) {
        return userMapper.toGuest(request);
    }

    @Override
    public RoleType getRole() {
        return RoleType.GUEST;
    }
}
