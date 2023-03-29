package br.com.cardapioDigital.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LINKS")
@EqualsAndHashCode(of = "id")
public class Links extends VersionedEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA")
    private Empresa empresa;

    @Column(name = "DESCRICAO", length = 300)
    private String descricao;

    @Column(name = "LINK", length = 100)
    private String link;
}
