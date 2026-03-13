package com.example.zsocial.backend.comment.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentFilterRequest {
    private Long postId;
}
