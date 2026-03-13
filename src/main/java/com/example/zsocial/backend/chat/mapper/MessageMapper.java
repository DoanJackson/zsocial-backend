package com.example.zsocial.backend.chat.mapper;

import com.example.zsocial.backend.chat.dto.request.ChatRequest;
import com.example.zsocial.backend.chat.dto.response.MessageResponse;
import com.example.zsocial.backend.chat.model.Message;
import com.example.zsocial.backend.media.dto.response.MediaBaseResponse;
import com.example.zsocial.backend.media.mapper.MediaMapper;
import com.example.zsocial.backend.media.model.Media;
import com.example.zsocial.backend.users.mapper.UserMapper;
import com.example.zsocial.backend.users.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MediaMapper.class, UserMapper.class})
public interface MessageMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "content", source = "request.content")
    @Mapping(target = "conversation.id", source = "conversationId")
    @Mapping(target = "sender", source = "sender")
    Message toMessage(ChatRequest request, Long conversationId, User sender);

    @Mapping(target = "conversationId", source = "message.conversation.id")
    @Mapping(target = "sender", source = "message.sender")
    @Mapping(target = "medias", source = "medias")
    MessageResponse toMessageResponse(Message message, List<Media> medias);
}
