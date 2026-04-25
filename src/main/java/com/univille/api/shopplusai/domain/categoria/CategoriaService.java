package com.univille.api.shopplusai.domain.categoria;

import com.univille.api.shopplusai.domain.categoria.dto.CreateCategoriaRequest;
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
}
