package com.example.zsocial.backend.infrastructure.websocket.validator;

import com.example.zsocial.backend.security.component.UserPrinciple;

public interface TopicValidator {
    //    Check if Validator has support topic path
    boolean supports(String destination);

    //    Check perrmission, throw Exception if you don't have permission
    void validate(UserPrinciple user, String destination);
}
