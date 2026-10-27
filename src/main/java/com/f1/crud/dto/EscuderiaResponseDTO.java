// criado (24/09/2026); Atualizações (06/10/2026); (23/10/2026)
package com.f1.crud.dto;

import java.time.LocalDate;
import java.util.Set;

public record EscuderiaResponseDTO(
    Long id,
    String nome,
    String paisOrigem,
    LocalDate dataFundacao,
    String fundador,
    Integer titulos,
    String urlLogo,
    String biografia,
    Set<CarroResponseDTO> carros,
    Set<PilotoResponseDTO> pilotos
) {}