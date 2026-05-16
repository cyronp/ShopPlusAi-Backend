package com.univille.api.shopplusai.domain.avaliacao;

import com.univille.api.shopplusai.domain.avaliacao.dto.AvaliacaoResponse;
import com.univille.api.shopplusai.domain.avaliacao.dto.CreateAvaliacaoRequest;
import com.univille.api.shopplusai.domain.produto.ProdutoRepository;
import com.univille.api.shopplusai.domain.usuario.UsuarioRepository;
import com.univille.api.shopplusai.infra.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;

    @Transactional
    public AvaliacaoResponse save(CreateAvaliacaoRequest dados){
        var usuario = usuarioRepository.findById(dados.idUsuario())
                .orElseThrow(() -> new NotFoundException("Não existe um usuário com esse id"));

        var produto = produtoRepository.findById(dados.idProduto())
                .orElseThrow(() -> new NotFoundException("Não existe um produto com esse id"));

        var avaliacao = new Avaliacao();
        avaliacao.setComentario(dados.comentario());
        avaliacao.setData(LocalDate.now());
        avaliacao.setUsuario(usuario);
        avaliacao.setProduto(produto);

        repository.save(avaliacao);
        return new AvaliacaoResponse(avaliacao);
    }

    @Transactional
    public void delete(Long id){
        var avaliacao = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Avaliacao não encontrada"));

        repository.delete(avaliacao);
    }

    public AvaliacaoResponse getById(Long id){
        var avaliacao = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Avaliacao não encontrada"));

        return new AvaliacaoResponse(avaliacao);
    }

    public Page<AvaliacaoResponse> getAll(Pageable paginacao){
        return repository.findAll(paginacao).map(AvaliacaoResponse::new);
    }

    public List<Avaliacao> getAllAvaliacoes(){
        return repository.findAll();
    }
}
