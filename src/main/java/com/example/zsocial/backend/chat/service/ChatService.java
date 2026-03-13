package com.example.zsocial.backend.chat.service;

import java.util.List;

import com.example.zsocial.backend.chat.dto.request.ChatRequest;
import com.example.zsocial.backend.chat.dto.request.ConversationLoadRequest;
import com.example.zsocial.backend.chat.dto.request.MessageLoadRequest;
import com.example.zsocial.backend.chat.dto.response.ConversationMembersResponse;
import com.example.zsocial.backend.chat.dto.response.ConversationResponse;
import com.example.zsocial.backend.chat.dto.response.MessageResponse;
import com.example.zsocial.backend.common.api.CursorResponse;

public interface ChatService {

    MessageResponse sendMessage(ChatRequest request);

    CursorResponse<ConversationResponse> getConversations(ConversationLoadRequest request);

    ConversationMembersResponse getConversationMembers(Long conversationId);

    CursorResponse<MessageResponse> getMessageConversation(MessageLoadRequest request);

    List<Long> getConversationMemberIds(Long conversationId);
}

