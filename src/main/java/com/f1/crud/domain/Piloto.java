package com.f1.crud.domain;     //  Criado (23/09/2026)

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_piloto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Piloto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String nacionalidade;
    private Integer numeroCarro;

    @ManyToMany
    @JoinTable(
        name = "tb_piloto_escuderia",
        joinColumns = @JoinColumn(name = "piloto_id"),
        inverseJoinColumns = @JoinColumn(name = "escuderia_id")
    )
    private Set<Escuderia> escuderias = new HashSet<>();
}