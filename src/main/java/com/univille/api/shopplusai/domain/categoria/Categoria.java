package com.univille.api.shopplusai.domain.categoria;

import com.univille.api.shopplusai.domain.categoria.dto.CreateCategoriaRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categorias")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    public Categoria(CreateCategoriaRequest dados) {
        this.nome = dados.nome();
    }
}
