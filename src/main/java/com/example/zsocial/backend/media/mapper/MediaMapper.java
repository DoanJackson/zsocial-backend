package com.example.zsocial.backend.media.mapper;

import com.example.zsocial.backend.infrastructure.filestorage.dto.UploadFileResult;
import com.example.zsocial.backend.media.dto.response.MediaBaseResponse;
import com.example.zsocial.backend.media.model.Media;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MediaMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "url", source = "publicUrl")
    @Mapping(target = "type", source = "mediaType")
    Media toMedia(UploadFileResult dto);

    MediaBaseResponse toMediaBaseResponse(Media media);
}
