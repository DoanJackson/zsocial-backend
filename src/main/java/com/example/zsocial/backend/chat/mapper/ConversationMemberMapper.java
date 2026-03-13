package com.example.zsocial.backend.chat.mapper;

import com.example.zsocial.backend.chat.dto.response.MemberResponse;
import com.example.zsocial.backend.chat.model.ConversationMember;
import com.example.zsocial.backend.media.mapper.MediaMapper;
import com.example.zsocial.backend.users.mapper.UserMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MediaMapper.class, UserMapper.class})
public interface ConversationMemberMapper {
    MemberResponse toMemberResponse(ConversationMember member);
}
