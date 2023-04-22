package br.com.cardapioDigital.dtos;

import br.com.cardapioDigital.models.Empresa;
import br.com.cardapioDigital.models.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CNPJ;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@Getter
@Setter
public class EmpresaDto {

    UUID id;

    //@NotEmpty(message = "{empresa.razaoSocial.campoObrigatorio}")
    private String razaoSocial;

    //@CNPJ(message = "{empresa.cnpj.invalido}")
    private String cnpj;

    // private byte[] logo;

    // private byte[] imagemCapa;

//    private String descricao;
//
//    private String site;
//
//    @URL(message = "{empresa.urlInstragramInvalida}")
//    private String instagram;

//    @URL(message = "{empresa.urlFacebookInvalida}")
//    private String facebook;

//    private String whatsapp;

    @NotBlank(message = "{empresa.telefoneContato.campoObrigatorio}")
    private String telefoneContato;

    @Email(message = "{empresa.email.invalido}")
    @NotBlank(message = "{empresa.email.campoObrigatorio}")
    private String email;

    public Empresa convertDtoToEntity() {
        return new ModelMapper().map(this, Empresa.class);
    }
}
