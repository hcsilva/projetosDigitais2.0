package br.com.cardapioDigital.dtos;

import br.com.cardapioDigital.enums.SimNaoEnum;
import br.com.cardapioDigital.models.Empresa;
import br.com.cardapioDigital.models.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@Getter
@Setter
public class UsuarioDto {

    UUID id;

    UUID idEmpresa;

    private EmpresaDto empresa;

    @Email
    @NotBlank(message = "{usuario.login.campoObrigatorio}")
    private String login;

    @NotBlank(message = "{usuario.senha.campoObrigatorio}")
    private String senha;

    private SimNaoEnum usuarioAtivo;

    @NotBlank(message = "{usuario.nome.campoObrigatorio}")
    private String nome;

    @NotBlank(message = "{usuario.telefone.campoObrigatorio}")
    private String telefone;

    public Usuario convertDtoToEntity() {
        return new ModelMapper().map(this, Usuario.class);
    }
}
