package com.f1.crud.repository;    // Criado (23/09/2026)   // Atualizado (24/09/2026)

import com.f1.crud.domain.Piloto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph; // Criado (02/10/2026)
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PilotoRepository extends JpaRepository<Piloto, Long> {

    // Carrega o piloto junto com suas escuderias para a tela de Perfil em 1 único SELECT
    @EntityGraph(attributePaths = {"escuderias"})
    Optional<Piloto> findByIdWithEscuderias(Long id);

    // Busca paginada por nome para a barra de busca da tela de Listagem
    Page<Piloto> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}