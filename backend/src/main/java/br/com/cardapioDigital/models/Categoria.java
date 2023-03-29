package br.com.cardapioDigital.models;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CATEGORIA")
@EqualsAndHashCode(of = "id")
public class Categoria extends VersionedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA")
    private Empresa empresa;

    @Column(name = "DESCRICAO", length = 100)
    private String descricaoSimples;

    @Column(name = "DESCRICAO_DETALHADA", length = 250)
    private String descricaoDetalhada;

    @Column(name = "LABEL_MINI_PRATO", length = 30)
    private String labelMiniPrato;

    @Column(name = "STATUS")
    private boolean status;
}
