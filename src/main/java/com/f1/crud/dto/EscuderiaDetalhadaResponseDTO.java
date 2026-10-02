// Criado (02/10/2026)
package com.f1.crud.dto;

import java.time.LocalDate;
import java.util.List;

public record EscuderiaDetalhadaResponseDTO(
    Long id,
    String nome,
    String paisOrigem,
    LocalDate dataFundacao,
    String fundador,
    Integer titulos,
    String biografia,
    String urlLogo,
    List<PilotoResumoDTO> pilotos,
    List<CarroResumoDTO> carros
) {}