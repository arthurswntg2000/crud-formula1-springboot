// Criado (24/09/2026); Atualizado (02/10/2026)
package com.f1.crud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CarroRequestDTO(
    @NotBlank(message = "O modelo é obrigatório") 
    String modelo,
    
    String motor,
    
    @NotNull(message = "O ano é obrigatório") 
    Integer ano,
    
    @Size(max = 2000, message = "A ficha técnica deve ter no máximo 2000 caracteres") 
    String fichaTecnica,
    
    String urlFoto,
    Long escuderiaId
) {}