package br.com.cardapioDigital.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutenticacaoDto {

    @NotBlank(message = "{usuario.username.campoObrigatorio}")
    String login;

    @NotBlank(message = "{usuario.password.campoObrigatorio}")
    String senha;
}