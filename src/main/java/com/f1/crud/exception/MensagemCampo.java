// Criado (02/10/2026)
package com.f1.crud.exception;

public record MensagemCampo(
    String fieldName,
    String message
) {}