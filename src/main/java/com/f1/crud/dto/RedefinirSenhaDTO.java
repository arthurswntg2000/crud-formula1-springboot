package com.f1.crud.dto;

import jakarta.validation.constraints.NotBlank;

public record RedefinirSenhaDTO(
    @NotBlank String login,
    @NotBlank String novaSenha
) {}