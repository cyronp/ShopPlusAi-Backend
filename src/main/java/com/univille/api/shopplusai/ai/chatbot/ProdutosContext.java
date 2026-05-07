package com.univille.api.shopplusai.ai.chatbot;

public record ProdutosContext(
        Long id,
        String nome,
        Long idCategoria,
        String categoria,
        double preco
) {
}
