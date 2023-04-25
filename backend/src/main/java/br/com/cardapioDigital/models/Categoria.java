package br.com.cardapioDigital.models;


import br.com.cardapioDigital.dtos.CategoriaDto;
import br.com.cardapioDigital.dtos.UsuarioDto;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    private UUID id;

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

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "categoria")
//    List<ItemCategoria> itensCategorias = new ArrayList<>();

    public CategoriaDto convertEntityToDto() {
        return new ModelMapper().map(this, CategoriaDto.class);
    }


}
