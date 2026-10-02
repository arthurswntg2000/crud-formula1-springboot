// criado (02/10/2026)
package com.f1.crud.dto;

import java.util.List;

public record PilotoDetalhadoResponseDTO(
    Long id,
    String nome,
    String nacionalidade,
    Integer numeroCarro,
    Integer idade, // Calculado no Service com base na dataNascimento
    Integer titulos,
    String status,
    String biografia,
    String urlFoto,
    List<EscuderiaResumoDTO> escuderias
) {}