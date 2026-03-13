package com.example.zsocial.backend.users.factory;

import com.example.zsocial.backend.users.dto.request.UserRegisterRequest;
import com.example.zsocial.backend.users.model.User;
import com.example.zsocial.backend.users.model.enums.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class UserFactory {

    private final Map<RoleType, UserCreator> creatorMap;

    @Autowired
    public UserFactory(List<UserCreator> creators) {
        creatorMap = new EnumMap<>(RoleType.class);
        for (UserCreator creator : creators) {
            creatorMap.put(creator.getRole(), creator);
        }
    }

    public User create(UserRegisterRequest request) {
        UserCreator creator = creatorMap.get(request.getRole());
        if (creator == null) {
            throw new IllegalArgumentException("Invalid role: " + request.getRole());
        }
        return creator.create(request);
    }

}
