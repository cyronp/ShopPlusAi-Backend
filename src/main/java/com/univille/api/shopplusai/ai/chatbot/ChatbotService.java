package com.univille.api.shopplusai.ai.chatbot;

import com.univille.api.shopplusai.ai.chatbot.dto.ChatResponse;
import com.univille.api.shopplusai.domain.avaliacao.AvaliacaoService;
import com.univille.api.shopplusai.domain.produto.ProdutoService;
import com.univille.api.shopplusai.infra.client.gemini.GeminiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatbotService {

    private final ProdutoService produtoService;
    private final AvaliacaoService avaliacaoService;
    private final ChatMemoryService memoryService;
    private final ChatbotPromptBuilder promptBuilder;
    private final GeminiClient geminiClient;
    private final ChatMessageRepository repository;

    public ChatResponse chat(String pergunta, String conversationId){

        if(conversationId == null){
            UUID uuid = UUID.randomUUID();
            conversationId = uuid.toString();

        }

        var avaliacoes = avaliacaoService.getAllAvaliacoes();
        var produtos = produtoService.getAllProdutos();

        var systemPrompt = promptBuilder.systemPrompt();
        var userPrompt = promptBuilder.contextPrompt(pergunta, getAllById(conversationId), avaliacoes, produtos);

        memoryService.saveUserMessage(conversationId, userPrompt);

        var iaResponse = geminiClient.chat(systemPrompt, userPrompt);

        memoryService.saveAssistantMessage(conversationId, iaResponse);

        return new ChatResponse();

    }

    public List<ChatMessage> getAllById(String conversationId){
        return repository.findAllByConversationId(conversationId);
    }

}
