// Criado (24/09/2026)
package com.f1.crud.dto;

public record PilotoRequestDTO(
    String nome,
    Integer numeroCarro,
    String nacionalidade,
    Long escuderiaId
) {}