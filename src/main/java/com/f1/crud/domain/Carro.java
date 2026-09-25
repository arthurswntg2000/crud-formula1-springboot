// Criado (22/09/2026)      // Atualizado (24/09/2026)
package com.f1.crud.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "carro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String modelo;

    private String motor;

    @Column(nullable = false)
    private Integer ano;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escuderia_id")
    private Escuderia escuderia;
}