// Criado (02/10/2026)
package com.f1.crud.exception;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ErroValidacao{
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
    private List<MensagemCampo> errors = new ArrayList<>();

    public ErroValidacao(Instant timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public Instant getTimestamp() { return timestamp; }
    public Integer getStatus() { return status; }
    public String getError() { return error; }
    public String getMessage() { return message; }
    public String getPath() { return path; }
    public List<MensagemCampo> getErrors() { return errors; }

    public void addError(String fieldName, String message) {
        errors.add(new MensagemCampo(fieldName, message));
    }
}