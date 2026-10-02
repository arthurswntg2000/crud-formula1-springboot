// Criado (02/10/2026)
package com.f1.crud.dto;

import java.util.List;

public record CarroDetalhadoResponseDTO(
    Long id,
    String modelo,
    String motor,
    Integer ano,
    String fichaTecnica,
    String urlFoto,
    String nomeEscuderia,
    List<PilotoResumoDTO> pilotosQueUsaram
) {}