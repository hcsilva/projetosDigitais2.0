package br.com.cardapioDigital.controllers;

import br.com.cardapioDigital.dtos.LinkWebDto;
import br.com.cardapioDigital.models.LinkWeb;
import br.com.cardapioDigital.services.LinkWebService;
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
@RequestMapping("api/link")
@SecurityRequirement(name = "bearer-key")
public class LinkWebController {

    @Autowired
    private LinkWebService linkService;

    @GetMapping("/{id}")
    public ResponseEntity<LinkWebDto> getById(@PathVariable UUID id) {
        var link = linkService.findById(id);
        return ResponseEntity.ok().body(link.convertEntityToDto());
    }

    @PostMapping
    public ResponseEntity<LinkWebDto> saveUser(@RequestBody @Valid LinkWebDto linkDto, UriComponentsBuilder uriComponentsBuilder) {
        var link = linkService.save(linkDto.convertDtoToEntity());
        var uri = uriComponentsBuilder.path("/api/link/{id}").buildAndExpand(link.getId()).toUri();
        return ResponseEntity.created(uri).body(link.convertEntityToDto());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LinkWebDto> updateUser(@PathVariable UUID id, @RequestBody @Valid LinkWebDto linkDto) {
        var link = linkService.findById(id);
        var linkAtualizado = linkDto.convertDtoToEntity();
        linkAtualizado.setId(link.getId());
        var linkSalvo = linkService.save(linkAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(linkSalvo.convertEntityToDto());
    }

    @GetMapping
    public ResponseEntity<Page<LinkWebDto>> findAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<LinkWeb> link = linkService.findAll(pageable);
        Page<LinkWebDto> linkDto = link.map(links -> new LinkWeb().convertEntityToDto());

        return ResponseEntity.ok().body(linkDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        var link = linkService.findById(id);
        linkService.delete(link);
        return ResponseEntity.noContent().build();
    }
}
