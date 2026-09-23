package com.f1.crud.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;   // Criado (23/09/2026)


@Entity
@Table(name = "escuderia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Escuderia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    private String paisOrigem;

    @OneToMany(mappedBy = "escuderia", cascade = CascadeType.ALL)
    private List<Carro> carros;


    // Criado (23/09/2026)
    @ManyToMany(mappedBy = "escuderias")
    private Set<Piloto> pilotos = new HashSet<>();
}