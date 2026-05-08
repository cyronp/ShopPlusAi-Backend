package com.univille.api.shopplusai.ai.chatbot.dto;

import jakarta.validation.constraints.NotBlank;

public record ChatRequest(
        @NotBlank
        String question
) {
}
