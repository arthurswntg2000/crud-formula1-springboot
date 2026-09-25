// Criado (24/09/2026)
package com.f1.crud.dto;

import java.util.Set;

public record PilotoResponseDTO(
    Long id,
    String nome,
    Integer numeroCarro,
    String nacionalidade,
    Set<String> nomesEscuderias // Lista/Conjunto com os nomes de todas as escuderias do piloto
) {}