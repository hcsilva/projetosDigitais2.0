package br.com.cardapioDigital.dtos;

import br.com.cardapioDigital.models.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class EnderecoDto {

    private UUID id;

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

    private LocalDateTime dataCriacaoRegistro;

    private LocalDateTime dataModificacaoRegistro;

    public Endereco convertDtoToEntity() {
        return new ModelMapper().map(this, Endereco.class);
    }
}
