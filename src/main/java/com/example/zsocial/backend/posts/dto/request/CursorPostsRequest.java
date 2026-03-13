package com.example.zsocial.backend.posts.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursorPostsRequest {
    private int size;
    private Long lastPostId;
}
