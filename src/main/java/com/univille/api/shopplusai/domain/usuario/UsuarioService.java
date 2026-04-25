package com.univille.api.shopplusai.domain.usuario;

import com.univille.api.shopplusai.domain.usuario.dto.CreateUsuarioRequest;
import com.univille.api.shopplusai.domain.usuario.dto.UsuarioResponse;
import com.univille.api.shopplusai.infra.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    @Transactional
    public UsuarioResponse save(CreateUsuarioRequest dados){
        var usuario = new Usuario(dados);
        repository.save(usuario);
        return new UsuarioResponse(usuario);
    }

    @Transactional
    public void delete(Long id){
        var usuario = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }

    public UsuarioResponse getById(Long id){
        var usuario = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        return new UsuarioResponse(usuario);
    }

    public Page<UsuarioResponse> getAll(Pageable paginacao){
        return repository.findAll(paginacao).map(UsuarioResponse::new);
    }
}
