package br.com.cardapioDigital.controllers;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/empresa")
@RolesAllowed("ROLE_ADMIN")
public class EmpresaController {

    @GetMapping
    public String buscarEmpresa(){
        return "Empresa X encontrada";
    }
}
