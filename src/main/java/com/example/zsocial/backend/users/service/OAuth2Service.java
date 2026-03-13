package com.example.zsocial.backend.users.service;

import com.example.zsocial.backend.users.dto.response.UserLoginResponse;

public interface OAuth2Service {

    public UserLoginResponse authenticateGoogle(String code);

}
