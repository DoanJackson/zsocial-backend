package com.example.zsocial.backend.users.mapper;

import com.example.zsocial.backend.users.dto.request.UserRegisterRequest;
import com.example.zsocial.backend.users.dto.response.UserDetailResponse;
import com.example.zsocial.backend.users.dto.response.UserSummaryResponse;
import com.example.zsocial.backend.users.model.Admin;
import com.example.zsocial.backend.users.model.Guest;
import com.example.zsocial.backend.users.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "status", constant = "ACTIVE")
    @Mapping(target = "followerCount", defaultValue = "0", ignore = true)
    @Mapping(target = "followingCount", defaultValue = "0", ignore = true)
    User toUser(UserRegisterRequest request);

    UserDetailResponse toUserDetailResponse(User user, boolean isFollowedByMe, boolean isFollowingMe);

    @Mapping(target = "status", constant = "ACTIVE")
    @Mapping(target = "followerCount", constant = "0L")
    @Mapping(target = "followingCount", constant = "0L")
    Guest toGuest(UserRegisterRequest request);

    @Mapping(target = "status", constant = "ACTIVE")
    @Mapping(target = "followerCount", constant = "0L")
    @Mapping(target = "followingCount", constant = "0L")
    Admin toAdmin(UserRegisterRequest request);

    @Mapping(target = "userId", source = "id")
    UserSummaryResponse toUserSummary(User user);
}
