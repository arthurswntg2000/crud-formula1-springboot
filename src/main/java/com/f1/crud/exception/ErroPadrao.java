package com.f1.crud.exception;     // Criado (22/09/2026)

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErroPadrao {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}