package br.com.cardapioDigital.controllers;

import br.com.cardapioDigital.dtos.EmpresaDto;
import br.com.cardapioDigital.exceptions.ValidacaoException;
import br.com.cardapioDigital.models.Empresa;
import br.com.cardapioDigital.services.EmpresaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
@RequestMapping("api/empresa")
@SecurityRequirement(name = "bearer-key")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<EmpresaDto> saveUser(@RequestBody @Valid EmpresaDto empresaDto, UriComponentsBuilder uriComponentsBuilder) {
        var empresaSalva = empresaService.save(empresaDto.convertDtoToEntity());

        var uri = uriComponentsBuilder.path("/api/empresa/{id}").buildAndExpand(empresaSalva.getId()).toUri();
        return ResponseEntity.created(uri).body(empresaSalva.convertEntityToDto());
    }

    @GetMapping
    public ResponseEntity<Page<EmpresaDto>> findAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Empresa> usuarios = empresaService.findAll(pageable);
        Page<EmpresaDto> usuariosDto = usuarios.map(empresa -> new Empresa().convertEntityToDto());

        return ResponseEntity.ok().body(usuariosDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDto> findById(@PathVariable UUID id) {
        Empresa empresa = empresaService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(empresa.convertEntityToDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable UUID id) {
        Empresa empresa = empresaService.findById(id);

        try {
            empresaService.delete(empresa);
        }catch (DataIntegrityViolationException e){
            throw new ValidacaoException("empresa.naoPodeSerExcluidaPoisExistemDependencias");
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDto> updateEmpresa(@PathVariable UUID id, @RequestBody @Valid EmpresaDto empresaDto) {
        var empresa = empresaService.findById(id);
        var empresaAtualizada = empresaDto.convertDtoToEntity();
        empresaAtualizada.setId(empresa.getId());
        var empresaSalva = empresaService.save(empresaAtualizada);

        return ResponseEntity.status(HttpStatus.OK).body(empresaSalva.convertEntityToDto());
    }

}
