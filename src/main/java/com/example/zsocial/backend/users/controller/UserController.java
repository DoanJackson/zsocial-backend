package com.example.zsocial.backend.users.controller;

import com.example.zsocial.backend.common.api.BaseResponse;
import com.example.zsocial.backend.common.api.CommonPage;
import com.example.zsocial.backend.common.api.ResultCode;
import com.example.zsocial.backend.common.dto.PaginationRequest;
import com.example.zsocial.backend.common.utils.FileUtils;
import com.example.zsocial.backend.common.utils.PaginationUtils;
import com.example.zsocial.backend.common.utils.SecurityUtils;
import com.example.zsocial.backend.media.dto.response.MediaBaseResponse;
import com.example.zsocial.backend.users.dto.response.UserDetailResponse;
import com.example.zsocial.backend.users.mapper.UserMapper;
import com.example.zsocial.backend.users.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "User endpoints")
public class UserController {

    private final UserService userService;

    @GetMapping("/search")
    public ResponseEntity<BaseResponse<CommonPage<UserDetailResponse>>> searchUsers(
            @RequestParam String keyword,
            @RequestParam(required = false, defaultValue = "id") String sortField,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "4") Integer size
    ) {
        final PaginationRequest paginationRequest = new PaginationRequest(page, size, sortField, sortDirection);
        CommonPage<UserDetailResponse> response = userService.searchUsers(keyword, PaginationUtils.getPageable(paginationRequest));
        return BaseResponse.success(response, ResultCode.SUCCESS, "Users fetched successfully");
    }

    @GetMapping("/me")
    public ResponseEntity<BaseResponse<UserDetailResponse>> getUserById() {

        UserDetailResponse response = userService.getUserProfileById(SecurityUtils.getCurrentUserId());

        return BaseResponse.success(response, ResultCode.SUCCESS, "User fetched successfully");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<BaseResponse<UserDetailResponse>> getUserProfileById(@PathVariable Long userId) {
        UserDetailResponse response = userService.getUserProfileById(userId);
        return BaseResponse.success(response, ResultCode.SUCCESS, "User fetched successfully");
    }

    @PatchMapping(value = "/me/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BaseResponse<MediaBaseResponse>> updateUserAvatar(@RequestParam("file") MultipartFile file) {
        // check file is image
        if (!FileUtils.isImage(file)) {
            return BaseResponse.failed(null, ResultCode.BAD_REQUEST, "File is not an image");
        }
        MediaBaseResponse response = userService.updateUserAvatar(file);
        return BaseResponse.success(response, ResultCode.SUCCESS, "User avatar updated successfully");
    }

    @PatchMapping(value = "/me/cover", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BaseResponse<MediaBaseResponse>> updateUserCover(@RequestParam("file") MultipartFile file) {
        // check file is image
        if (!FileUtils.isImage(file)) {
            return BaseResponse.failed(null, ResultCode.BAD_REQUEST, "File is not an image");
        }
        MediaBaseResponse response = userService.updateUserCover(file);
        return BaseResponse.success(response, ResultCode.SUCCESS, "User cover updated successfully");
    }
}
