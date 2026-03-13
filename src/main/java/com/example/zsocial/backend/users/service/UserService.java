package com.example.zsocial.backend.users.service;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.example.zsocial.backend.common.api.CommonPage;
import com.example.zsocial.backend.media.dto.response.MediaBaseResponse;
import com.example.zsocial.backend.users.dto.request.UserRegisterRequest;
import com.example.zsocial.backend.users.dto.response.UserDetailResponse;
import com.example.zsocial.backend.users.dto.response.UserLoginResponse;
import com.example.zsocial.backend.users.model.User;

public interface UserService {

    public User register(UserRegisterRequest request);

    public UserLoginResponse login(String username, String password);

    public User loadUserByUsername(String username);

    public User getUserById(Long userId);

    public UserDetailResponse getUserProfileById(Long userId);

    public CommonPage<UserDetailResponse> searchUsers(String query, Pageable pageable);
    
    public MediaBaseResponse updateUserAvatar(MultipartFile file);

    public MediaBaseResponse updateUserCover(MultipartFile file);
}
