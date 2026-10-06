// Criado (23/09/2026) ;  Atualizado (02/10/2026)
package com.f1.crud.repository;    

import com.f1.crud.domain.Escuderia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EscuderiaRepository extends JpaRepository<Escuderia, Long> {

    // Carrega a escuderia com seus pilotos e carros vinculados para a tela de Perfil
    @EntityGraph(attributePaths = {"pilotos", "carros"})
    @Query("SELECT e FROM Escuderia e WHERE e.id = :id")
    Optional<Escuderia> findByIdWithRelacionamentos(@Param("id") Long id);

    @Query("SELECT e FROM Escuderia e WHERE LOWER(e.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    Page<Escuderia> buscarPorNome(@Param("nome") String nome, Pageable pageable);
}