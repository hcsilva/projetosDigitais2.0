package br.com.cardapioDigital.models;

import br.com.cardapioDigital.dtos.ItemExtrasDto;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.UUID;

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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "ID_ITEM_CATEGORIA")
    private ItemCategoria itemCategoria;

    @Column(name = "DESCRICAO", length = 250)
    private String descricao;

    public ItemExtrasDto convertEntityToDto() {
        return new ModelMapper().map(this, ItemExtrasDto.class);
    }
}
