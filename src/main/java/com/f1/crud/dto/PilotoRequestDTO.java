// Criado (24/09/2026);  // Atualizado (02/10/2026)
package com.f1.crud.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Set;

public record PilotoRequestDTO(
    @NotBlank(message = "O nome é obrigatório") 
    String nome,
    
    String nacionalidade,
    Integer numeroCarro,
    LocalDate dataNascimento,
    Integer titulos,
    String status,
    
    String biografia,
    
    String urlFoto,
    Set<Long> escuderiasIds
) {}