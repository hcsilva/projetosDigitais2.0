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

import java.util.UUID;

@RestController
@RequestMapping("api/endereco")
@SecurityRequirement(name = "bearer-key")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDto> getById(@PathVariable UUID id) {
        var endereco = enderecoService.findById(id);
        return ResponseEntity.ok().body(endereco.convertEntityToDto());
    }

    @GetMapping("/cep/{id}")
    public ResponseEntity<EnderecoDto> getDadosByCep(@PathVariable Integer id) {
        var endereco = enderecoService.findByCep(id);
        return ResponseEntity.ok().body(endereco);
    }

    @PostMapping
    public ResponseEntity<EnderecoDto> saveEndereco(@RequestBody @Valid EnderecoDto enderecoDto, UriComponentsBuilder uriComponentsBuilder) {
        var endereco = enderecoDto.convertDtoToEntity();
        var enderecoSalvo = enderecoService.save(endereco);
        var uri = uriComponentsBuilder.path("/api/usuario/{id}").buildAndExpand(enderecoSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(enderecoSalvo.convertEntityToDto());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDto> updateEndereco(@PathVariable UUID id, @RequestBody @Valid EnderecoDto enderecoDto) {
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
    public ResponseEntity<Void> deleteEndereco(@PathVariable UUID id) {
        var user = enderecoService.findById(id);
        enderecoService.delete(user);
        return ResponseEntity.noContent().build();
    }
}
