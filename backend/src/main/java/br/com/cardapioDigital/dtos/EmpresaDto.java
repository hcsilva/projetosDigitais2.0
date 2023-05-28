package br.com.cardapioDigital.dtos;

import br.com.cardapioDigital.models.Empresa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CNPJ;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class EmpresaDto {

    private UUID id;

    private EnderecoDto endereco;

    @NotEmpty(message = "{empresa.razaoSocial.campoObrigatorio}")
    private String nomeEstabelecimento;

    @CNPJ(message = "{empresa.cnpj.invalido}")
    private String cnpj;

     private byte[] logo;

     private byte[] imagemCapa;

    private String descricao;

    private String site;

    @URL(message = "{empresa.urlInstragramInvalida}")
    private String instagram;

    @URL(message = "{empresa.urlFacebookInvalida}")
    private String facebook;

    private String whatsapp;

    @NotBlank(message = "{empresa.telefoneContato.campoObrigatorio}")
    private String telefoneContato;

    @Email(message = "{empresa.email.invalido}")
    @NotBlank(message = "{empresa.email.campoObrigatorio}")
    private String email;

    private LocalDateTime dataCriacaoRegistro;

    private LocalDateTime dataModificacaoRegistro;

    public Empresa convertDtoToEntity() {
        return new ModelMapper().map(this, Empresa.class);
    }
}
