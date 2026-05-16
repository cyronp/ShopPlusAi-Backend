package com.univille.api.shopplusai.domain.produto;

import com.univille.api.shopplusai.domain.categoria.CategoriaRepository;
import com.univille.api.shopplusai.domain.produto.dto.CreateProdutoRequest;
import com.univille.api.shopplusai.domain.produto.dto.ProdutoResponse;
import com.univille.api.shopplusai.domain.usuario.dto.CreateUsuarioRequest;
import com.univille.api.shopplusai.domain.usuario.dto.UsuarioResponse;
import com.univille.api.shopplusai.infra.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;
    private final CategoriaRepository categoriaRepository;

    @Transactional
    public ProdutoResponse save(CreateProdutoRequest dados){

        var categoria = categoriaRepository.findById(dados.idCategoria())
                        .orElseThrow(() -> new NotFoundException("Não existe nenhuma categoria com esse id"));
        var produto = new Produto();
        produto.setNome(dados.nome());
        produto.setCategoria(categoria);
        produto.setPreco(dados.preco());
        repository.save(produto);
        return new ProdutoResponse(produto);
    }

    @Transactional
    public void delete(Long id){
        var produto = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));
        repository.delete(produto);
    }

    public ProdutoResponse getById(Long id){
        var produto = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));
        return new ProdutoResponse(produto);
    }

    public Page<ProdutoResponse> getAll(Pageable paginacao){
        return repository.findAll(paginacao).map(ProdutoResponse::new);
    }

    public List<Produto> getAllProdutos(){
        return repository.findAll();
    }
}
