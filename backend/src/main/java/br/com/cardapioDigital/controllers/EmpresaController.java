package br.com.cardapioDigital.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/empresa")
@SecurityRequirement(name = "bearer-key")
public class EmpresaController {

    @GetMapping
    public ResponseEntity getAllEmpresas(){
        return ResponseEntity.ok("você deu um getAll em todas as empresas");
    }




}
