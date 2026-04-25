package com.univille.api.shopplusai.controller;

import com.univille.api.shopplusai.domain.usuario.UsuarioService;
import com.univille.api.shopplusai.domain.usuario.dto.CreateUsuarioRequest;
import com.univille.api.shopplusai.domain.usuario.dto.UsuarioResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioResponse> save(@RequestBody @Valid CreateUsuarioRequest dados, UriComponentsBuilder uriBuilder){
        var usuarioResponse = service.save(dados);
        var uri = uriBuilder.path("/{id}").buildAndExpand(usuarioResponse.id()).toUri();
        return ResponseEntity.created(uri).body(usuarioResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> getById(@PathVariable Long id){
        var usuarioResponse = service.getById(id);
        return ResponseEntity.ok().body(usuarioResponse);
    }
}
