package com.univille.api.shopplusai.ai.chatbot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChatRequest(
        @NotBlank
        String question,

        @NotNull
        Long usuarioId,

        String conversationId

) {
}
