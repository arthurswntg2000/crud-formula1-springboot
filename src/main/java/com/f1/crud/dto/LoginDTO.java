// Criado (28/09/2026)
package com.f1.crud.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
    @NotBlank(message = "O login é obrigatório")
    String login,

    @NotBlank(message = "A palavra-passe é obrigatória")
    String password
) {}