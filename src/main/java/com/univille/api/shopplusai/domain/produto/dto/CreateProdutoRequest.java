package com.univille.api.shopplusai.domain.produto.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateProdutoRequest (

        @NotBlank
        String nome,

        @NotBlank
        Long idCategoria,

        @NotBlank
        double preco)
{
}
