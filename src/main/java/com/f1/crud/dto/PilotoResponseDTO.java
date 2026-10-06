// Criado (24/09/2026); Atualizações (06/10/2026)
package com.f1.crud.dto;

import java.time.LocalDate;
import java.util.Set;

public record PilotoResponseDTO(
    Long id,
    String nome,
    String nacionalidade,
    Integer numeroCarro,
    LocalDate dataNascimento,
    Integer titulos,
    String status,
    String biografia,
    String urlFoto,
    Set<String> nomesEscuderias // Lista/Conjunto com os nomes de todas as escuderias do piloto
) {}