package br.com.cardapioDigital.dtos;

import br.com.cardapioDigital.models.ItemExtras;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemExtrasDto {

    private UUID id;

    @NotNull(message = "{itemExtras.empresa.campoObrigatorio}")
    private UUID idEmpresa;

    @NotNull(message = "{itemExtras.categoria.campoObrigatorio}")
    private UUID idCategoria;

    @NotBlank(message = "{itemExtras.descricao.campoObrigatorio}")
    private String descricao;

    public ItemExtras convertDTOToEntity() {
        return new ModelMapper().map(this, ItemExtras.class);
    }
}
