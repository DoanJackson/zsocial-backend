package com.example.zsocial.backend.posts.service;

import com.example.zsocial.backend.common.api.CommonPage;
import com.example.zsocial.backend.common.api.CursorResponse;
import com.example.zsocial.backend.posts.dto.request.CursorPostsRequest;
import com.example.zsocial.backend.posts.dto.request.PostsCreateRequest;
import com.example.zsocial.backend.posts.dto.request.PostsSearchRequest;
import com.example.zsocial.backend.posts.dto.request.PostsUpdateRequest;
import com.example.zsocial.backend.posts.dto.response.PostResponse;
import com.example.zsocial.backend.posts.dto.response.PostsDetailResponse;
import com.example.zsocial.backend.posts.model.Posts;
import org.springframework.data.domain.Pageable;

public interface PostsService {

    void createPost(PostsCreateRequest request);

    void updatePost(Long postId, PostsUpdateRequest request);

    void deletePost(Long postId);

    boolean isUserCommentPost(Long userId, Posts posts);

    PostsDetailResponse getPostDetail(Long postId);

    CursorResponse<PostResponse> getFriendsPostsCursor(Long userId, CursorPostsRequest request);

    CursorResponse<PostResponse> getSuggestedPostsCursor(Long userId, CursorPostsRequest request);

    CursorResponse<PostResponse> searchPosts(PostsSearchRequest searchRequest);

    CommonPage<PostResponse> getUserPosts(Long userId, Pageable pageable);

}
