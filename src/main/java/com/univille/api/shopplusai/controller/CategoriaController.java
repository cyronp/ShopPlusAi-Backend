package com.univille.api.shopplusai.controller;

import com.univille.api.shopplusai.domain.categoria.Categoria;
import com.univille.api.shopplusai.domain.categoria.CategoriaService;
import com.univille.api.shopplusai.domain.categoria.dto.CreateCategoriaRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService service;

    @PostMapping
    public ResponseEntity<Categoria> save(@RequestBody @Valid CreateCategoriaRequest dados, UriComponentsBuilder uriBuilder){
        var categoria = service.save(dados);
        var uri = uriBuilder.path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getbyId(@PathVariable Long id){
        var categoria = service.getById(id);
        return ResponseEntity.ok().body(categoria);
    }

}
