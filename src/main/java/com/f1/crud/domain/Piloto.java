//  Criado (23/09/2026)     // Atualizado (24/09/2026)
package com.f1.crud.domain;     

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_piloto")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Piloto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
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