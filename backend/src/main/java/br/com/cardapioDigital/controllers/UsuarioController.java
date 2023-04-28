package br.com.cardapioDigital.controllers;


import br.com.cardapioDigital.dtos.UsuarioDto;
import br.com.cardapioDigital.enums.SimNaoEnum;
import br.com.cardapioDigital.models.Usuario;
import br.com.cardapioDigital.services.EmpresaService;
import br.com.cardapioDigital.services.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/api/usuario")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> getById(@PathVariable UUID id) {
        var usuario = usuarioService.findById(id);
        var usuarioDto = new UsuarioDto();
        BeanUtils.copyProperties(usuario, usuarioDto);
        return ResponseEntity.ok().body(usuarioDto);
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> saveUser(@RequestBody @Valid UsuarioDto usuarioDto, UriComponentsBuilder uriComponentsBuilder) {
        usuarioDto.setUsuarioAtivo(SimNaoEnum.SIM);
        usuarioDto.setSenha(usuarioService.passwordEncoder(usuarioDto.getSenha()));

        var usuarioSalvo = usuarioService.save(usuarioDto.convertDtoToEntity());

        var uri = uriComponentsBuilder.path("/api/usuario/{id}").buildAndExpand(usuarioSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(usuarioSalvo.convertEntityToDto());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> updateUser(@PathVariable UUID id, @RequestBody @Valid UsuarioDto usuarioDto) {
        var usuario = usuarioService.findById(id);
        var usuarioAtualizado = usuarioDto.convertDtoToEntity();
        usuarioAtualizado.setId(usuario.getId());
        var usuarioSalvo = usuarioService.save(usuarioAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(usuarioSalvo.convertEntityToDto());
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioDto>> findAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Usuario> usuarios = usuarioService.findAll(pageable);
        Page<UsuarioDto> usuariosDto = usuarios.map(usuario -> new Usuario().convertEntityToDto());

        return ResponseEntity.ok().body(usuariosDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        var user = usuarioService.findById(id);
        usuarioService.delete(user);
        return ResponseEntity.noContent().build();
    }

}
