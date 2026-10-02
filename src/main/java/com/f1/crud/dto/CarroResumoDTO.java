// Criado (02/10/2026)
package com.f1.crud.dto;

public record CarroResumoDTO(
    Long id,
    String modelo,
    String motor,
    Integer ano,
    String nomeEscuderia
) {}