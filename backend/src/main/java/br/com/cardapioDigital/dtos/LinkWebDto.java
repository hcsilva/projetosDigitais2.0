package br.com.cardapioDigital.dtos;


import br.com.cardapioDigital.models.LinkWeb;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class LinkWebDto {

    @NotEmpty(message = "{links.descricao.campoObrigatorio}")
    private String descricao;

    @URL(message = "{links.urlInvalida}")
    @NotEmpty(message = "{links.link.campoObrigatorio}")
    private String link;

    @NotNull(message = "{links.empresa.campoObrigatorio}")
    private EmpresaDto empresa;

    public LinkWeb convertDtoToEntity() {
        return new ModelMapper().map(this, LinkWeb.class);
    }

}
