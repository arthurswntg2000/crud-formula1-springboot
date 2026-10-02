// Criado (22/09/2026)      // Atualizado (24/09/2026)
package com.f1.crud.domain;

import org.hibernate.annotations.Formula;
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

    @Formula("(SELECT e.nome FROM escuderia e WHERE e.id = escuderia_id)")
    private String nomeEscuderia;

    @Column(nullable = false)
    private String modelo;

    private String motor;

    @Column(nullable = false)
    private Integer ano;

    @Column(length = 2000)
    private String fichaTecnica;

    private String urlFoto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escuderia_id")
    private Escuderia escuderia;
}