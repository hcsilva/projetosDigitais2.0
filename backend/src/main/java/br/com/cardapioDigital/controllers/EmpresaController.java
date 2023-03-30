package br.com.cardapioDigital.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/empresa")
@RolesAllowed("ROLE_ADMIN")
@SecurityRequirement(name = "bearer-key")
public class EmpresaController {






}
