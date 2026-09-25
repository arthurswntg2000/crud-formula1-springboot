// Criado (24/09/2026)
package com.f1.crud.dto;

public record CarroRequestDTO(
    String modelo,
    String motor,
    Integer ano,
    Long escuderiaId
) {}