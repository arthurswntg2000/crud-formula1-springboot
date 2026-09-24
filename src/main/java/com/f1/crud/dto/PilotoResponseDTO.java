// Criado (24/09/2026)
package com.f1.crud.dto;

public record PilotoResponseDTO(
    Long id,
    String nome,
    Integer numeroCarro,
    String nacionalidade,
    String nomeEscuderia
) {}