package br.com.cardapioDigital.models;

import br.com.cardapioDigital.dtos.ItemCategoriaDto;
import br.com.cardapioDigital.dtos.UsuarioDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ITEM_CATEGORIA")
@EqualsAndHashCode(of = "id")
public class ItemCategoria extends VersionedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA")
    private Empresa empresa;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_CATEGORIA")
    private Categoria categoria;

    @Column(name = "FOTO")
    @Lob
    private byte[] foto;

    @Column(name = "DESCRICAO_DETALHADA", length = 250)
    private String descricaoDetalhada;

    @Column(name = "DESCRICAO_SIMPLES", length = 100)
    private String descricaoSimples;

    @Column(name = "PRECO")
    private BigDecimal preco;

    @Column(name = "PRECO_MINI_PRATO")
    private BigDecimal precoMiniPrato;

    @Column(name = "SERVE_QUANTIDADE_PESSOAS")
    private Integer serveQuantidadePessoas;

    @Column(name = "SEM_LACTOSE")
    private boolean semLactose;

    @Column(name = "VEGETARIANO")
    private boolean vegetariano;

    @Column(name = "SEM_GLUTEN")
    private boolean semGluten;

    @Column(name = "VEGANO")
    private boolean vegano;

    @Column(name = "DESTAQUE")
    private boolean destaque;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "itemCategoria")
//    private List<ItemExtras> itensExtras = new ArrayList<>();

    public ItemCategoriaDto convertEntityToDto() {
        return new ModelMapper().map(this, ItemCategoriaDto.class);
    }
}
