package br.com.cardapioDigital.dtos;

import br.com.cardapioDigital.models.FormaPagamento;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class FormaPagamentoDto {

    private UUID id;

    @NotNull(message = "{formaPagamento.empresa.campoObrigatorio}")
    private UUID empresaId;
    private boolean dinheiro;
    private boolean cartaoCredito;
    private boolean cartaoDebito;
    private boolean valeRefeicao;
    @NotNull(message = "{formaPagamento.visivel.campoObrigatorio}")
    private boolean visivel;

    public FormaPagamento convertDtoToEntity() {
        return new ModelMapper().map(this, FormaPagamento.class);
    }
}
