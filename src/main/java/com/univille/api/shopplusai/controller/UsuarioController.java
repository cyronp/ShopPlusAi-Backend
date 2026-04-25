package com.univille.api.shopplusai.controller;

import com.univille.api.shopplusai.domain.usuario.UsuarioService;
import com.univille.api.shopplusai.domain.usuario.dto.CreateUsuarioRequest;
import com.univille.api.shopplusai.domain.usuario.dto.UsuarioResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
}
