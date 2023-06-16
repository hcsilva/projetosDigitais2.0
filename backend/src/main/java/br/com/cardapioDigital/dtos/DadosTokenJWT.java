package br.com.cardapioDigital.dtos;

import java.util.UUID;

public record DadosTokenJWT(String token, UUID empresaId, String name) {
}