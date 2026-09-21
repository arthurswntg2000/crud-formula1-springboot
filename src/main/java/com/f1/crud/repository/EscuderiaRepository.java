package com.f1.crud.repository;

import com.f1.crud.domain.Escuderia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscuderiaRepository extends JpaRepository<Escuderia, Long> {
}