package com.univille.api.shopplusai.ai.chatbot;

import com.univille.api.shopplusai.ai.chatbot.dto.ChatRequest;
import com.univille.api.shopplusai.ai.chatbot.dto.ChatResponse;
import com.univille.api.shopplusai.ai.chatbot.dto.ConversationsUserResponse;
import com.univille.api.shopplusai.domain.avaliacao.AvaliacaoService;
import com.univille.api.shopplusai.domain.produto.ProdutoService;
import com.univille.api.shopplusai.domain.usuario.Usuario;
import com.univille.api.shopplusai.domain.usuario.UsuarioRepository;
import com.univille.api.shopplusai.infra.client.gemini.GeminiClient;
import com.univille.api.shopplusai.infra.exception.NotFoundException;
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
    private final ChatConversationRepository conversationRepository;
    private final UsuarioRepository usuarioRepository;

    public ChatResponse chat(ChatRequest request){

        ChatConversation conversation;
        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new NotFoundException("Usuario com esse id não encontrado"));

        if (request.conversationId() == null){
            conversation = new ChatConversation();
            conversation.setUsuario(usuario);
            conversation = conversationRepository.save(conversation);
        }else{
            UUID conversationId = UUID.fromString(request.conversationId());
            conversation = conversationRepository.findById(conversationId)
                    .orElseThrow(() -> new NotFoundException("Conversa com esse id não encontrada"));
        }

        String pergunta = request.question();

        var avaliacoes = avaliacaoService.getAllAvaliacoes();
        var produtos = produtoService.getAllProdutos();

        var systemPrompt = promptBuilder.systemPrompt();
        var userPrompt = promptBuilder.contextPrompt(pergunta, getAllMessages(conversation.getId()), avaliacoes, produtos);
        var messageUser = memoryService.saveUserMessage(conversation, pergunta);

        var iaResponse = geminiClient.chat(systemPrompt, userPrompt);
        var messageAssistent = memoryService.saveAssistantMessage(conversation, iaResponse);

        return new ChatResponse(messageAssistent);
    }

    public List<ChatResponse> getAllMessagesByConversationId(String id){
        return getAllMessages(UUID.fromString(id))
                .stream()
                .map(ChatResponse::new)
                .toList();
    }

    public List<ChatMessage> getAllMessages(UUID conversationId){
        var conversation = conversationRepository.getReferenceById(conversationId);
        return repository.findAllByConversation(conversation);
    }

    public List<ConversationsUserResponse> getAllConversationsByUsuarioId(Long usuarioId){

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado com o id informado"));

        var conversations= conversationRepository.findAllByUsuarioId(usuarioId);
        return conversations.stream()
                .map(ConversationsUserResponse::new)
                .toList();
    }

}
