package com.example.zsocial.backend.chat.mapper;

import com.example.zsocial.backend.chat.dto.response.ConversationResponse;
import com.example.zsocial.backend.chat.model.Conversation;
import com.example.zsocial.backend.media.mapper.MediaMapper;
import com.example.zsocial.backend.users.mapper.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MediaMapper.class, UserMapper.class})
public interface ConversationMapper {
    //    avatar, name, isGroup
    @Mapping(target = "avatar", source = "groupAvatar.url")
    ConversationResponse toConversationResponse(Conversation conversation);
}
