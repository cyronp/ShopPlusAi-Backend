package com.univille.api.shopplusai.ai.chatbot.dto;

import com.univille.api.shopplusai.ai.chatbot.ChatConversation;

import java.time.LocalDateTime;

public record ConversationsUserResponse(
        Long usuarioId,
        String conversationId,
        LocalDateTime createdAt
) {
    public ConversationsUserResponse(ChatConversation conversation){
        this(conversation.getUsuario().getId(), conversation.getId().toString(), conversation.getCreatedAt());
    }
}
