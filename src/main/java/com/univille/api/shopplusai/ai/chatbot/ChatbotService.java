package com.univille.api.shopplusai.ai.chatbot;

import com.univille.api.shopplusai.ai.chatbot.dto.ChatResponse;
import com.univille.api.shopplusai.domain.avaliacao.AvaliacaoService;
import com.univille.api.shopplusai.domain.produto.ProdutoService;
import com.univille.api.shopplusai.infra.client.gemini.GeminiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatbotService {

    private final ProdutoService produtoService;
    private final AvaliacaoService avaliacaoService;
    private final ChatMemoryService memoryService;
    private final ChatbotPromptBuilder promptBuilder;
    private final GeminiClient geminiClient;

    public ChatResponse chat(String pergunta, String conversationId){



    }

}
