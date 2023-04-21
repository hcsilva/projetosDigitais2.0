package br.com.cardapioDigital.controllers;

import br.com.cardapioDigital.dtos.FormaPagamentoDto;
import br.com.cardapioDigital.models.FormaPagamento;
import br.com.cardapioDigital.services.FormaPagamentoService;
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
@RequestMapping("api/formaPagamento")
@SecurityRequirement(name = "bearer-key")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamentoDto> getById(@PathVariable Long id) {
        var formaPagamento = formaPagamentoService.findById(id);
        return ResponseEntity.ok().body(formaPagamento.convertEntityToDto());
    }

    @PostMapping
    public ResponseEntity<FormaPagamentoDto> saveUser(@RequestBody @Valid FormaPagamentoDto formaPagamentoDto, UriComponentsBuilder uriComponentsBuilder) {
        var formaPagamentoSalvo = formaPagamentoService.save(formaPagamentoDto.convertDtoToEntity());
        var uri = uriComponentsBuilder.path("/api/formaPagamento/{id}").buildAndExpand(formaPagamentoSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(formaPagamentoSalvo.convertEntityToDto());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormaPagamentoDto> updateUser(@PathVariable Long id, @RequestBody @Valid FormaPagamentoDto formaPagamentoDto) {
        var formaPagamento = formaPagamentoService.findById(id);
        var formaPagamentoAtualizado = formaPagamentoDto.convertDtoToEntity();
        formaPagamentoAtualizado.setId(formaPagamento.getId());
        var formaPagamentoSalvo = formaPagamentoService.save(formaPagamentoAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoSalvo.convertEntityToDto());
    }

    @GetMapping
    public ResponseEntity<Page<FormaPagamentoDto>> findAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<FormaPagamento> formaPagamentos = formaPagamentoService.findAll(pageable);
        Page<FormaPagamentoDto> formaPagamentoDtos = formaPagamentos.map(formaPagamento -> new FormaPagamento().convertEntityToDto());

        return ResponseEntity.ok().body(formaPagamentoDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        var formaPagamento = formaPagamentoService.findById(id);
        formaPagamentoService.delete(formaPagamento);
        return ResponseEntity.noContent().build();
    }
}
