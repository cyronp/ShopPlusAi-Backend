package com.univille.api.shopplusai.controller;

import com.univille.api.shopplusai.ai.chatbot.ChatbotService;
import com.univille.api.shopplusai.ai.chatbot.dto.ChatRequest;
import com.univille.api.shopplusai.ai.chatbot.dto.ChatResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
