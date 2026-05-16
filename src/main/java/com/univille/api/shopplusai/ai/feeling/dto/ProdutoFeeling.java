package com.univille.api.shopplusai.ai.feeling.dto;

public record ProdutoFeeling(
        String nome,
        String categoria,
        String sentimento,
        Double media,
        String comentarioDestaque
) {
}
