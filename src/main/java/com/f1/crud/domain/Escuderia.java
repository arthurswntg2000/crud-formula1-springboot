package com.f1.crud.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
}