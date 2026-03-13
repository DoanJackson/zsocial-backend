package com.example.zsocial.backend.chat.event;

import com.example.zsocial.backend.chat.dto.response.ConversationSocketResponse;
import com.example.zsocial.backend.chat.dto.response.MessageResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageSaveEvent {

    private MessageResponse messageResponse;

    private ConversationSocketResponse conversationSocketResponse;

}
