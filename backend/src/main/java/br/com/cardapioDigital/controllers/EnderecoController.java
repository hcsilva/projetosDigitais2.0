package br.com.cardapioDigital.controllers;

import br.com.cardapioDigital.dtos.EnderecoDto;
import br.com.cardapioDigital.models.Endereco;
import br.com.cardapioDigital.services.EnderecoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/endereco")
@SecurityRequirement(name = "bearer-key")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDto> getById(@PathVariable Long id) {
        var endereco = enderecoService.findById(id);
        return ResponseEntity.ok().body(endereco.convertEntityToDto());
    }

    @PostMapping
    public ResponseEntity<EnderecoDto> saveUser(@RequestBody @Valid EnderecoDto enderecoDto, UriComponentsBuilder uriComponentsBuilder) {
        var enderecoSalvo = enderecoService.save(enderecoDto.convertDtoToEntity());
        var uri = uriComponentsBuilder.path("/api/usuario/{id}").buildAndExpand(enderecoSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(enderecoSalvo.convertEntityToDto());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDto> updateUser(@PathVariable Long id, @RequestBody @Valid EnderecoDto enderecoDto) {
        var endereco = enderecoService.findById(id);
        var enderecoAtualizado = enderecoDto.convertDtoToEntity();
        enderecoAtualizado.setId(endereco.getId());
        var enderecoSalvo = enderecoService.save(enderecoAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(enderecoSalvo.convertEntityToDto());
    }

    @GetMapping
    public ResponseEntity<Page<EnderecoDto>> findAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Endereco> usuarios = enderecoService.findAll(pageable);
        Page<EnderecoDto> usuariosDto = usuarios.map(endereco -> new Endereco().convertEntityToDto());

        return ResponseEntity.ok().body(usuariosDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        var user = enderecoService.findById(id);
        enderecoService.delete(user);
        return ResponseEntity.noContent().build();
    }
}
