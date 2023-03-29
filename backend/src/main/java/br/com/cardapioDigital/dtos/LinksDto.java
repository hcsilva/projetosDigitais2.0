package br.com.cardapioDigital.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

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

}
