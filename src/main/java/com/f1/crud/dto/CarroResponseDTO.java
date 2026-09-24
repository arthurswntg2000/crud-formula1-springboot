package com.f1.crud.dto;

public record CarroResponseDTO(
    Long id,
    String modelo,
    String motor,
    Integer ano,
    String nomeEscuderia
) {}