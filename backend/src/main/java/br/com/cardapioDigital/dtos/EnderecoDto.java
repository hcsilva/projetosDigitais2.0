package br.com.cardapioDigital.dtos;

import br.com.cardapioDigital.enums.PaisEnum;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EnderecoDto {

    @NotBlank(message = "{endereco.cep.campoObrigatorio}")
    @Size(max = 8, message = "{endereco.cep.tamanhoMaximoExcedido}")
    private int cep;

    @NotBlank(message = "{endereco.logradouro.campoObrigatorio}")
    private String logradouro;

    @NotNull(message = "{endereco.numero.campoObrigatorio}")
    private int numero;

    @NotBlank(message = "{endereco.bairro.campoObrigatorio}")
    private String bairro;

    private String complemento;

    @NotBlank(message = "{endereco.cidade.campoObrigatorio}")
    private String cidade;

    @NotBlank(message = "{endereco.estado.campoObrigatorio}")
    private String estado;

    @NotNull(message = "{endereco.empresa.campoObrigatorio}")
    private EmpresaDto empresa;

    private String pais;

    public EnderecoDto() {
        this.pais = PaisEnum.BRA.getTwoDigitsCode();
    }
}
