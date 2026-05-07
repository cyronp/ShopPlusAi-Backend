package com.univille.api.shopplusai.domain.produto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProdutoRequest (

        @NotBlank
        String nome,

        @NotNull
        Long idCategoria,

        @NotNull
        double preco)
{
}
