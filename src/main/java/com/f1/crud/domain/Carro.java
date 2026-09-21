package com.f1.crud.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "carro")
@Data
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

    @ManyToOne
    @JoinColumn(name = "escuderia_id")
    @JsonIgnoreProperties("carros")
    private Escuderia escuderia;
}