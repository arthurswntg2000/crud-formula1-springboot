// Criado (24/09/2026);  //  Atualizado (02/10/2026)
package com.f1.crud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

public record EscuderiaRequestDTO(
    @NotBlank(message = "O nome da escuderia é obrigatório") 
    String nome,
    
    String paisOrigem,
    LocalDate dataFundacao,
    String fundador,
    Integer titulos,
    
    @Size(max = 2000, message = "A biografia deve ter no máximo 2000 caracteres") 
    String biografia,
    
    String urlLogo,
    Set<Long> pilotoIds,
    Set<Long> carroIds
) {}