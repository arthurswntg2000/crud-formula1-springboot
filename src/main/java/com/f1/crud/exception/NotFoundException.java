package com.f1.crud.exception;  // Criado (22/09/2026)

public class NotFoundException extends RuntimeException {
    public NotFoundException(String mensagem) {
        super(mensagem);
    }
}