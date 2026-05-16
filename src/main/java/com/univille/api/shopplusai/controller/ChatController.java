package com.univille.api.shopplusai.controller;

import com.univille.api.shopplusai.ai.chatbot.ChatMessage;
import com.univille.api.shopplusai.ai.chatbot.ChatbotService;
import com.univille.api.shopplusai.ai.chatbot.dto.ChatRequest;
import com.univille.api.shopplusai.ai.chatbot.dto.ChatResponse;
import com.univille.api.shopplusai.ai.chatbot.dto.ConversationsUserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatbotService service;

    @PostMapping
    public ResponseEntity<ChatResponse> message(@RequestBody @Valid ChatRequest message){
        var response = service.chat(message);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<ConversationsUserResponse>> conversationByUser(@RequestParam Long usuarioId){
        var conversations = service.getAllConversationsByUsuarioId(usuarioId);
        return ResponseEntity.ok().body(conversations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ChatResponse>> conversationById(@PathVariable String id){
        var messages = service.getAllMessagesByConversationId(id);
        return ResponseEntity.ok().body(messages);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteConversation(@PathVariable String id){
        service.removeConversation(id);
        return ResponseEntity.noContent().build();
    }



}
