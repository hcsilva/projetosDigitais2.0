package br.com.cardapioDigital.models;

import br.com.cardapioDigital.dtos.LinkWebDto;
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
@Table(name = "LINKS")
@EqualsAndHashCode(of = "id")
public class LinkWeb extends VersionedEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="ID_EMPRESA")
    private UUID empresaId;

    @Column(name = "DESCRICAO", length = 300)
    private String descricao;

    @Column(name = "URL", length = 100)
    private String url;

    public LinkWebDto convertEntityToDto() {
        return new ModelMapper().map(this, LinkWebDto.class);
    }

}
