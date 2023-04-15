package br.com.cardapioDigital.dtos;

import br.com.cardapioDigital.enums.SimNaoEnum;
import br.com.cardapioDigital.models.Usuario;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class UsuarioDto {

    Long id;

    @NotBlank(message = "{usuario.login.campoObrigatorio}")
    String login;

    @NotBlank(message = "{usuario.senha.campoObrigatorio}")
    String senha;

    @NotBlank(message = "{usuario.ativo.campoObrigatorio}")
    SimNaoEnum usuarioAtivo;

    @NotBlank(message = "{usuario.nome.campoObrigatorio}")
    String nome;

    @NotBlank(message = "{usuario.telefone.campoObrigatorio}")
    String telefone;

    public Usuario convertDtoToEntity() {
        return new ModelMapper().map(this, Usuario.class);
    }
}
