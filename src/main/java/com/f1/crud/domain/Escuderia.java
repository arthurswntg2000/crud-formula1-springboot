// criado (22/09/2026)  // Atualizações (24/09/2026)
package com.f1.crud.domain;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
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
@SQLDelete(sql = "UPDATE escuderia SET ativo = false WHERE id = ?")
@SQLRestriction("ativo = true")
public class Escuderia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;
    private String paisOrigem;
    private LocalDate dataFundacao;
    private String fundador;
    private Integer titulos = 0;

    @Column(length = 2000)
    private String biografia;

    private String urlLogo;

    @Column(nullable = false)
    private Boolean ativo = true;

    @OneToMany(mappedBy = "escuderia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Carro> carros = new ArrayList<>();

    // Criado (23/09/2026)
    @ManyToMany(mappedBy = "escuderias")
    private Set<Piloto> pilotos = new HashSet<>();
}