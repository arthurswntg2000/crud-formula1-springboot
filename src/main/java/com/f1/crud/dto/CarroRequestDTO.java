// Criado (24/09/2026); Atualizado (02/10/2026)
package com.f1.crud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

public record CarroRequestDTO(
    @NotBlank String modelo,
    String motor,
    @NotNull Integer ano,
    Long escuderiaId,
    Set<Long> pilotoIds,
    String urlFoto,
    String fotoUrl,
    String fichaTecnica
) {}