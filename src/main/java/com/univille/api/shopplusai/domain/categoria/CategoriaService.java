package com.univille.api.shopplusai.domain.categoria;

import com.univille.api.shopplusai.domain.categoria.dto.CreateCategoriaRequest;
import com.univille.api.shopplusai.infra.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    @Transactional
    public Categoria save(CreateCategoriaRequest dados){
        var categoria = new Categoria(dados);
        repository.save(categoria);
        return categoria;
    }

    @Transactional
    public void delete(Long id){
        var categoria = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));
        repository.delete(categoria);
    }
}
