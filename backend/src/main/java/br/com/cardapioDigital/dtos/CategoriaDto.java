package br.com.cardapioDigital.dtos;

import br.com.cardapioDigital.models.Categoria;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoriaDto {

    private UUID id;

    @NotNull(message = "{categoria.empresa.campoObrigatorio}")
    private UUID empresaId;

    @NotBlank(message = "{categoria.descricao.campoObrigatorio}")
    private String descricaoSimples;

    @NotBlank(message = "{categoria.descricaoDetalhada.campoObrigatorio}")
    private String descricaoDetalhada;

    @Schema(description = "Categoria Ativa ou não")
    @NotNull(message = "{categoria.status.campoObrigatorio}")
    private boolean status;

    private String labelMiniPrato;

    public Categoria convertDTOToEntity() {
        return new ModelMapper().map(this, Categoria.class);
    }
}
