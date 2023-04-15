package br.com.cardapioDigital.controllers;


import br.com.cardapioDigital.dtos.UsuarioDto;
import br.com.cardapioDigital.services.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> getById(@PathVariable Long id){
        var usuario = usuarioService.findById(id);
        var usuarioDto = new UsuarioDto();
        BeanUtils.copyProperties(usuario, usuarioDto);
        return ResponseEntity.ok().body(usuarioDto);
    }

    @PostMapping
    public ResponseEntity createUsuario(){
        return ResponseEntity.ok().body("Usuário Criado com sucesso");
    }

}
