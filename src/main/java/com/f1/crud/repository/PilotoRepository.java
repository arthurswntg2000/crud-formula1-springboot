package com.f1.crud.repository;    // Criado (23/09/2026)

import com.f1.crud.domain.Piloto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;     // Criado (24/09/2026)
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;      // Criado (24/09/2026)


public interface PilotoRepository extends JpaRepository<Piloto, Long> {

    // Criado (24/09/2026)
    @Override
    @EntityGraph(attributePaths = "escuderias")
    Page<Piloto> findAll(Pageable pageable);

    // Criado (24/09/2026)
    @Override
    @EntityGraph(attributePaths = "escuderias")
    Optional<Piloto> findById(Long id);
}