package br.com.cardapioDigital.dtos;

import br.com.cardapioDigital.models.Empresa;
import io.swagger.v3.oas.models.links.Link;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class LinksDto {

    @NotEmpty(message = "{links.descricao.campoObrigatorio}")
    private String descricao;

    @URL(message = "{links.urlInvalida}")
    @NotEmpty(message = "{links.link.campoObrigatorio}")
    private String link;

    @NotNull(message = "{links.empresa.campoObrigatorio}")
    private EmpresaDto empresa;

    public Link convertDtoToEntity() {
        return new ModelMapper().map(this, Link.class);
    }

}
