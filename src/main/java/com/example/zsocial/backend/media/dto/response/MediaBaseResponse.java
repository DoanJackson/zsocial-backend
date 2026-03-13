package com.example.zsocial.backend.media.dto.response;

import com.example.zsocial.backend.media.model.enums.MediaType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MediaBaseResponse {
    private long id;
    private String url;
    private MediaType type;
}
