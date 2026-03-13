package com.example.zsocial.backend.posts.mapper;

import com.example.zsocial.backend.media.dto.response.MediaBaseResponse;
import com.example.zsocial.backend.media.mapper.MediaMapper;
import com.example.zsocial.backend.media.model.Media;
import com.example.zsocial.backend.posts.dto.request.PostsCreateRequest;
import com.example.zsocial.backend.posts.dto.request.PostsUpdateRequest;
import com.example.zsocial.backend.posts.dto.response.PostResponse;
import com.example.zsocial.backend.posts.dto.response.PostsDetailResponse;
import com.example.zsocial.backend.posts.model.Posts;
import com.example.zsocial.backend.users.mapper.UserMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MediaMapper.class, UserMapper.class})
public interface PostsMapper {

    @Mapping(target = "user.id", source = "userId")
    Posts toPosts(PostsCreateRequest request, Long userId);

    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void updatePostsFromRequest(PostsUpdateRequest request, @MappingTarget Posts posts);

    @Mapping(target = "author", source = "posts.user")
    PostsDetailResponse toPostsDetailResponse(Posts posts, List<Media> medias, Long commentCount);

    @Mapping(target = "author", source = "posts.user")
    @Mapping(target = "isFollowed", defaultValue = "false")
    @Mapping(target = "medias", source = "medias")
    PostResponse toPostResponse(Posts posts, List<Media> medias, Long commentCount, boolean isFollowed);

}
