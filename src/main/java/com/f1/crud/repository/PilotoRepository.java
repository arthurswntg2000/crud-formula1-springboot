package com.f1.crud.repository;    // Criado (23/09/2026)

import com.f1.crud.domain.Piloto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PilotoRepository extends JpaRepository<Piloto, Long> {
}