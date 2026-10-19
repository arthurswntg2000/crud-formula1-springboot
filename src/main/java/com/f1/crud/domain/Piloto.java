//  Criado (23/09/2026)     // Atualizado (24/09/2026)
package com.f1.crud.domain;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_piloto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE tb_piloto SET ativo = false WHERE id = ?")
@SQLRestriction("ativo = true")
public class Piloto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
    private String nacionalidade;
    private Integer numeroCarro;
    private LocalDate dataNascimento;
    private Integer titulos = 0;
    private String status; // Ex: "EM ATIVIDADE", "APOSENTADO"[cite: 7, 10]

    @Column(length = 2000)
    private String biografia; // Biografia com limite amplo[cite: 8, 10]

    private String urlFoto;

    @Column(nullable = false)
    private Boolean ativo = true;

    @ManyToMany
    @JoinTable(
        name = "tb_piloto_escuderia",
        joinColumns = @JoinColumn(name = "piloto_id"),
        inverseJoinColumns = @JoinColumn(name = "escuderia_id")
    )
    private Set<Escuderia> escuderias = new HashSet<>();
}