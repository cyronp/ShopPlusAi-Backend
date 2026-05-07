package com.univille.api.shopplusai.ai.chatbot;

import com.univille.api.shopplusai.domain.avaliacao.Avaliacao;
import com.univille.api.shopplusai.domain.produto.Produto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChatbotPromptBuilder {

    public String contextPrompt(String pergunta, List<Avaliacao> avaliacoes, List<Produto> produtos){
        String promptText = produtos.stream().map(produto -> """
                |Produto: 
                Nome: %s
                Categoria: %s,
                Preço: %s|
                """.formatted(
                        produto.getNome(),
//                        produto.ge
        ));
    }
}
