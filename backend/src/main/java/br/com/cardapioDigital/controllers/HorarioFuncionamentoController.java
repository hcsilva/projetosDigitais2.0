package br.com.cardapioDigital.controllers;

import br.com.cardapioDigital.dtos.HorarioFuncionamentoDto;
import br.com.cardapioDigital.models.HorarioFuncionamento;
import br.com.cardapioDigital.services.HorarioFuncionamentoService;
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
@RequestMapping("api/horarioFuncionamento")
@SecurityRequirement(name = "bearer-key")
public class HorarioFuncionamentoController {

    @Autowired
    private HorarioFuncionamentoService horarioFuncionamentoService;

    @GetMapping("/{id}")
    public ResponseEntity<HorarioFuncionamentoDto> getById(@PathVariable Long id) {
        var horarioFuncionamento = horarioFuncionamentoService.findById(id);
        return ResponseEntity.ok().body(horarioFuncionamento.convertEntityToDto());
    }

    @PostMapping
    public ResponseEntity<HorarioFuncionamentoDto> saveUser(@RequestBody @Valid HorarioFuncionamentoDto horarioFuncionamentoDto, UriComponentsBuilder uriComponentsBuilder) {
        var horarioFuncionamentoSalvo = horarioFuncionamentoService.save(horarioFuncionamentoDto.convertDtoToEntity());
        var uri = uriComponentsBuilder.path("/api/horarioFuncionamento/{id}").buildAndExpand(horarioFuncionamentoSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(horarioFuncionamentoSalvo.convertEntityToDto());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioFuncionamentoDto> updateUser(@PathVariable Long id, @RequestBody @Valid HorarioFuncionamentoDto horarioFuncionamentoDto) {
        var horarioFuncionamento = horarioFuncionamentoService.findById(id);
        var horarioFuncionamentoAtualizado = horarioFuncionamentoDto.convertDtoToEntity();
        horarioFuncionamentoAtualizado.setId(horarioFuncionamento.getId());
        var horarioFuncionamentoSalvo = horarioFuncionamentoService.save(horarioFuncionamentoAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(horarioFuncionamentoSalvo.convertEntityToDto());
    }

    @GetMapping
    public ResponseEntity<Page<HorarioFuncionamentoDto>> findAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<HorarioFuncionamento> horariosFuncionamento = horarioFuncionamentoService.findAll(pageable);
        Page<HorarioFuncionamentoDto> horariosFuncionamentoDto = horariosFuncionamento.map(horarioFuncionamento -> new HorarioFuncionamento().convertEntityToDto());

        return ResponseEntity.ok().body(horariosFuncionamentoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        var horarioFuncionamento = horarioFuncionamentoService.findById(id);
        horarioFuncionamentoService.delete(horarioFuncionamento);
        return ResponseEntity.noContent().build();
    }
}
