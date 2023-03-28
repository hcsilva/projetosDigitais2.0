package br.com.cardapioDigital.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/empresa")
@RolesAllowed("ROLE_ADMIN")
@SecurityRequirement(name = "bearer-key")
public class EmpresaController {

    @GetMapping
    @Operation(summary = "Busca Empresa X")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao buscar empresa"),
            @ApiResponse(responseCode = "404", description = "Nao encontrada")
    })
    public ResponseEntity<String> buscarEmpresa(){
        return  ResponseEntity.ok("Empresa X encontrada");
    }
}
