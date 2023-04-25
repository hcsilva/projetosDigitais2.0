package br.com.cardapioDigital.dtos;

import br.com.cardapioDigital.models.HorarioFuncionamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HorarioFuncionamentoDto {

    private UUID id;

    @NotNull(message = "{horariofuncionamento.empresa.campoObrigatorio}")
    private Long empresaId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime segundaInicial;

    private UUID empresa_id;

    public HorarioFuncionamento convertDtoToEntity() {
        return new ModelMapper().map(this, HorarioFuncionamento.class);
    }
}
