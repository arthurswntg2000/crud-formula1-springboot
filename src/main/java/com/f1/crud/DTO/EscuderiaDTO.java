package com.f1.crud.DTO;        // Criado (23/09/2026)

import com.f1.crud.domain.Escuderia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EscuderiaDTO {

    private String nome;
    private String paisOrigem;

    // Construtor que converte a Entidade em DTO
    public EscuderiaDTO(Escuderia escuderia) {
        this.nome = escuderia.getNome();
        this.paisOrigem = escuderia.getPaisOrigem();
    }
}
