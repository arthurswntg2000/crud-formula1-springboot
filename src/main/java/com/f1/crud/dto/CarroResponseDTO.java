// Criado (24/09/2026);  Atualizações (06/10/2026); (27/10/2026); (28/10/2026)
package com.f1.crud.dto;

import java.util.Set;

public record CarroResponseDTO(
    Long id,
    String modelo,
    String motor,
    Integer ano,
    String urlFoto,
    String fichaTecnica,
    String nomeEscuderia,
    Long escuderiaId, 
    Set<PilotoResponseDTO> pilotos
) {}