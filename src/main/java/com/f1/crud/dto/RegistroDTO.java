// Criado (07/10/2026)
package com.f1.crud.dto;

import jakarta.validation.constraints.NotBlank;

public record RegistroDTO(
    @NotBlank(message = "O nome é obrigatório")
    String nome,

    @NotBlank(message = "O login/e-mail é obrigatório")
    String login,

    @NotBlank(message = "A senha é obrigatória")
    String password
) {}
