package com.example.zsocial.backend.comment.service;

import com.example.zsocial.backend.comment.dto.request.CommentCreateRequest;
import com.example.zsocial.backend.comment.dto.request.CommentUpdateRequest;
import com.example.zsocial.backend.comment.dto.response.CommentBaseResponse;
import com.example.zsocial.backend.comment.model.Comment;
import com.example.zsocial.backend.common.api.CommonPage;
import org.springframework.data.domain.Pageable;


public interface CommentService {

    void createComment(Long postId, CommentCreateRequest request);

    void updateComment(Long commentId, CommentUpdateRequest request);

    void deleteComment(Long commentId);

    CommonPage<CommentBaseResponse> getCommentsByPostId(Long postId, Pageable pageable);

    CommonPage<CommentBaseResponse> getRepliesByCommentId(Long commentId, Pageable pageable);

    boolean isDeletedByUser(Long userId, Comment comment);

}
