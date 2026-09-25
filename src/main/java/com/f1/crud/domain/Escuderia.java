// criado (22/09/2026)  // Atualizado (24/09/2026)
package com.f1.crud.domain;

import jakarta.persistence.*;
import lombok.*;    
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "escuderia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Escuderia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    private String paisOrigem;

    @OneToMany(mappedBy = "escuderia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Carro> carros = new ArrayList<>();

    // Criado (23/09/2026)
    @ManyToMany(mappedBy = "escuderias")
    private Set<Piloto> pilotos = new HashSet<>();
}