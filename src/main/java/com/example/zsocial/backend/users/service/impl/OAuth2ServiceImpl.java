package com.example.zsocial.backend.users.service.impl;

import com.example.zsocial.backend.common.api.ResultCode;
import com.example.zsocial.backend.common.exception.ApiException;
import com.example.zsocial.backend.config.GoogleOAuthConfig;
import com.example.zsocial.backend.security.util.JwtUtil;
import com.example.zsocial.backend.users.dto.response.UserLoginResponse;
import com.example.zsocial.backend.users.model.Guest;
import com.example.zsocial.backend.users.model.User;
import com.example.zsocial.backend.users.model.UserOAuthToken;
import com.example.zsocial.backend.users.model.enums.ProviderType;
import com.example.zsocial.backend.users.model.enums.RoleType;
import com.example.zsocial.backend.users.model.enums.UserStatus;
import com.example.zsocial.backend.users.repository.TokenRepository;
import com.example.zsocial.backend.users.repository.UserRepository;
import com.example.zsocial.backend.users.service.OAuth2Service;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OAuth2ServiceImpl implements OAuth2Service {
    private final GoogleOAuthConfig googleOAuthConfig;

    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;

    private final JwtUtil jwtUtil;

    @Transactional
    public UserLoginResponse authenticateGoogle(String code) {
        try {
            // 1. Đổi Code lấy Token từ Google
            GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(new NetHttpTransport(),
                    GsonFactory.getDefaultInstance(), "https://oauth2.googleapis.com/token",
                    googleOAuthConfig.getClientId(), googleOAuthConfig.getClientSecret(), code,
                    googleOAuthConfig.getRedirectUri()).execute();

            // 2. Lấy thông tin User từ Google
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(), GsonFactory.getDefaultInstance())
                    .setAudience(Collections.singletonList(googleOAuthConfig.getClientId()))
                    .build();
            GoogleIdToken idToken = verifier.verify(tokenResponse.getIdToken());
            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();

            // 3. Kiểm tra User có tồn tại không
            User user = userRepository.findByEmail(email).orElseGet(() -> {
                Guest newUser = Guest.builder()
                        .email(email)
                        .fullName((String) payload.get("name"))
                        .role(RoleType.GUEST)
                        .status(UserStatus.ACTIVE)
                        .googleId(payload.getSubject())
                        .build();
                return userRepository.save(newUser);
            });

            // Lưu REFRESH TOKEN vào database
            UserOAuthToken tokenEntity = tokenRepository.findByUserIdAndProvider(user.getId(), ProviderType.GOOGLE)
                    .orElse(new UserOAuthToken());
            tokenEntity.setUser(user);
            tokenEntity.setAccessToken(tokenResponse.getAccessToken());
            tokenEntity.setProvider(ProviderType.GOOGLE);
            if (tokenResponse.getRefreshToken() != null) {
                tokenEntity.setRefreshToken(tokenResponse.getRefreshToken());
            }
            tokenRepository.save(tokenEntity);

            String jwt = jwtUtil.generateToken(user.getUsername(), user.getRole().name(), user.getId());
            return UserLoginResponse.builder()
                    .token(jwt)
                    .userId(user.getId())
                    .role(user.getRole())
                    .fullName(user.getFullName())
                    .build();
        } catch (IOException | GeneralSecurityException e) {
            throw new ApiException(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
