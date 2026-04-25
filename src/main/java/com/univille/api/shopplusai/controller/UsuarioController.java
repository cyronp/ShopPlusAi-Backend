package com.univille.api.shopplusai.controller;

import com.univille.api.shopplusai.domain.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    
}
