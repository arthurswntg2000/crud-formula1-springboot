// Criado (24/09/2026)
package com.f1.crud.dto;

import java.util.Set;

public record PilotoRequestDTO(
    String nome,
    Integer numeroCarro,
    String nacionalidade,
    Set<Long> escuderiasIds // Conjunto de IDs das escuderias do piloto
) {}