package com.univille.api.shopplusai.ai.feeling.dto;

import com.univille.api.shopplusai.domain.produto.dto.ProdutoResponse;

import java.util.List;

public record FeelingResponse(
        Resumo resumo,
        List<SentimentChart> sentimentChart,
        MediaSentimento mediaSentimento,
        ProdutoFeeling melhorProduto,
        ProdutoFeeling piorProduto,
        List<Aspecto> aspectos
) {
}
