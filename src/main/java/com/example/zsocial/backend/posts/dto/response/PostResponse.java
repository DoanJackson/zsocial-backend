package com.example.zsocial.backend.posts.dto.response;

import com.example.zsocial.backend.media.dto.response.MediaBaseResponse;
import com.example.zsocial.backend.posts.model.enums.PostStatus;
import com.example.zsocial.backend.users.dto.response.UserSummaryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private PostStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private UserSummaryResponse author;

    private Long commentCount;

    private List<MediaBaseResponse> medias;

    private boolean isFollowed;
}
