package br.com.cardapioDigital.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ITEM_EXTRAS")
@EqualsAndHashCode(of = "id")
public class ItemExtras extends VersionedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "ID_ITEM_CATEGORIA")
    private ItemCategoria itemCategoria;

    @Column(name = "DESCRICAO", length = 250)
    private String descricao;
}
