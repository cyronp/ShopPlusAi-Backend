package com.univille.api.shopplusai.ai.chatbot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatMemoryService {

    private final ChatMessageRepository repository;

    public ChatMessage saveUserMessage(
            ChatConversation conversation,
            String mensagem
    ){

        ChatMessage message = new ChatMessage();

        message.setConversation(conversation);
        message.setRole(MessageRole.USER);
        message.setContent(mensagem);
        message.setCreatedAt(LocalDateTime.now());

        message = repository.save(message);
        return message;
    }

    public ChatMessage saveAssistantMessage(
            ChatConversation conversation,
            String mensagem
    ){

        ChatMessage message = new ChatMessage();

        message.setConversation(conversation);
        message.setRole(MessageRole.ASSISTANT);
        message.setContent(mensagem);
        message.setCreatedAt(LocalDateTime.now());

        message = repository.save(message);
        return message;
    }
}
