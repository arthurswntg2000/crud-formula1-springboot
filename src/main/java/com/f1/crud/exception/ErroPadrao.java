// Criado (22/09/2026); // Atualizado (02/10/2026)
package com.f1.crud.exception;

import java.time.Instant;

public record ErroPadrao(
    Instant timestamp,
    Integer status,
    String error,
    String message,
    String path
) {}