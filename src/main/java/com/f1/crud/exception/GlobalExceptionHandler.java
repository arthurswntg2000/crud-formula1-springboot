// Criado (22/09/2026)      // Atualizado (24/09/2026)
package com.f1.crud.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Trata recurso não encontrado (HTTP 404)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErroPadrao> notFound(NotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        
        ErroPadrao err = new ErroPadrao(
                Instant.now(),
                status.value(),
                "Recurso não encontrado",
                e.getMessage(),
                request.getRequestURI()
        );
        
        return ResponseEntity.status(status).body(err);
    }

    // Criado (24/09/2026)
    // Trata falhas de validação nos DTOs com @Valid (HTTP 400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadrao> validationError(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        String mensagensErro = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        ErroPadrao err = new ErroPadrao(
                Instant.now(),
                status.value(),
                "Erro de Validação",
                mensagensErro,
                request.getRequestURI()
        );
        
        return ResponseEntity.status(status).body(err);
    }

    // Criado (24/09/2026)
    // Trata erros genéricos e não mapeados (HTTP 500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroPadrao> genericError(Exception e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ErroPadrao err = new ErroPadrao(
                Instant.now(),
                status.value(),
                "Erro Interno no Servidor",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }
}   