package br.com.cardapioDigital.dtos;


import br.com.cardapioDigital.models.LinkWeb;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@Getter
@Setter
public class LinkWebDto {

    private UUID id;

    @NotEmpty(message = "{links.descricao.campoObrigatorio}")
    private String descricao;

    @NotEmpty(message = "{links.link.campoObrigatorio}")
    private String url;

    private UUID empresaId;

    public LinkWeb convertDtoToEntity() {
        return new ModelMapper().map(this, LinkWeb.class);
    }
}
