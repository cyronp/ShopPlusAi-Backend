package com.univille.api.shopplusai.ai.chatbot.dto;

import com.univille.api.shopplusai.ai.chatbot.ChatMessage;
import com.univille.api.shopplusai.ai.chatbot.MessageRole;

import java.time.LocalDateTime;

public record ChatResponse(
        Long id,
        String conversationId,
        MessageRole role,
        String content,
        LocalDateTime createdAt
) {
    public ChatResponse(ChatMessage message){
        this(message.getId(), message.getConversation().getId().toString(), message.getRole(), message.getContent(), message.getCreatedAt());
    }
}
