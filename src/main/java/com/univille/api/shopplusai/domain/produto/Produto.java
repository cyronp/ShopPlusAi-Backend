package com.univille.api.shopplusai.domain.produto;


import com.univille.api.shopplusai.domain.produto.dto.CreateProdutoRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "produtos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Produto{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, name = "id_categoria")
    private Long idCategoria;

    @Column(nullable = false)
    private double preco;

    public Produto(CreateProdutoRequest dados){
        this.nome = dados.nome();
        this.idCategoria = dados.idCategoria();
        this.preco = dados.preco();
    }
}
