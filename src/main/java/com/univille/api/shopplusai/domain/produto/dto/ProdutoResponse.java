package com.univille.api.shopplusai.domain.produto.dto;

import com.univille.api.shopplusai.domain.produto.Produto;


public record ProdutoResponse(Long id, String nome, Long idCategoria, double preco){
    public ProdutoResponse(Produto produto){
        this(produto.getId(), produto.getNome(), produto.getIdCategoria(), produto.getPreco());}
    }

