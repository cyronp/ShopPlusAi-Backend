package com.univille.api.shopplusai.controller;


import com.univille.api.shopplusai.domain.produto.Produto;
import com.univille.api.shopplusai.domain.produto.ProdutoService;
import com.univille.api.shopplusai.domain.produto.dto.CreateProdutoRequest;
import com.univille.api.shopplusai.domain.produto.dto.ProdutoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aspectj.apache.bcel.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoResponse> save(@RequestBody @Valid CreateProdutoRequest dados, UriComponentsBuilder uriBuilder) {
        var produto = service.save(dados);
        var uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.id()).toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> getById(@PathVariable Long id) {
        var produto = service.getById(id);
        return ResponseEntity.ok().body(produto);
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoResponse>> getAll(@PageableDefault Pageable paginacao) {
        var page = service.getAll(paginacao);
        return ResponseEntity.ok().body(page);
    }
}
