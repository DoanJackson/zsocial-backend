package com.example.zsocial.backend.infrastructure.websocket.handler;

import com.example.zsocial.backend.common.api.ResultCode;
import com.example.zsocial.backend.common.exception.ApiException;
import com.example.zsocial.backend.infrastructure.websocket.validator.TopicValidator;
import com.example.zsocial.backend.security.component.UserPrinciple;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.Principal;
import java.util.List;

import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SubscribeHandler implements StompCommandHandler {

    private final List<TopicValidator> topicValidators;

    @Override
    public StompCommand getCommand() {
        return StompCommand.SUBSCRIBE;
    }

    @Override
    public void handle(StompHeaderAccessor accessor) {
        String destination = accessor.getDestination();

        if (accessor.getUser() == null || !(accessor.getUser() instanceof UsernamePasswordAuthenticationToken))
            throw new ApiException(ResultCode.FORBIDDEN, "User not authenticated");
        UserPrinciple userPrinciple = (UserPrinciple) ((UsernamePasswordAuthenticationToken) accessor.getUser()).getPrincipal();
        log.debug("User {} subscribed to {}", userPrinciple.getUsername(), destination);

        boolean isHandled = false;
        for (TopicValidator validator : topicValidators) {
            if (validator.supports(destination)) {
                validator.validate(userPrinciple, destination);
                isHandled = true;
                break;
            }
        }
        if (!isHandled) throw new ApiException(ResultCode.FORBIDDEN, "Unknown topic destination: " + destination);
    }
}
