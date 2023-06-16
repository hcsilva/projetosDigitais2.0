package br.com.cardapioDigital.controllers;

import br.com.cardapioDigital.dtos.AutenticacaoDto;
import br.com.cardapioDigital.dtos.DadosTokenJWT;
import br.com.cardapioDigital.models.Usuario;
import br.com.cardapioDigital.services.TokenService;
import br.com.cardapioDigital.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid AutenticacaoDto usuarioDto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(usuarioDto.getLogin(), usuarioDto.getSenha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        var usuario = usuarioService.findUsuarioByLogin(usuarioDto.getLogin());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT, usuario.getEmpresa().getId(), usuario.getNome()));
    }
}