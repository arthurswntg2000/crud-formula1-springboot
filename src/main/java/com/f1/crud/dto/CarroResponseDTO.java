// Criado (24/09/2026);  Atualizações (06/10/2026)
package com.f1.crud.dto;

public record CarroResponseDTO(
    Long id,
    String modelo,
    String motor,
    Integer ano,
    String fichaTecnica,
    String urlFoto,
    String nomeEscuderia
) {}