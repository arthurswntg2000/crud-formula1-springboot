package com.f1.crud.dto;    //      Criado (23/09/2026)

import com.f1.crud.domain.Escuderia;
import com.f1.crud.domain.Piloto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PilotoDTO {

    private String nome;
    private String nacionalidade;
    private Integer numeroCarro;
    private Set<String> nomesEscuderias;

    public PilotoDTO(Piloto piloto) {
        this.nome = piloto.getNome();
        this.nacionalidade = piloto.getNacionalidade();
        this.numeroCarro = piloto.getNumeroCarro();
        if (piloto.getEscuderias() != null) {
            this.nomesEscuderias = piloto.getEscuderias().stream()
                    .map(Escuderia::getNome)
                    .collect(Collectors.toSet());
        }
    }
}