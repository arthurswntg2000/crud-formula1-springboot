package com.f1.crud.repository;    // Criado (23/09/2026) 

import com.f1.crud.domain.Escuderia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EscuderiaRepository extends JpaRepository<Escuderia, Long> {

    // Método utilitário para busca rápida por nome
    Optional<Escuderia> findByNomeIgnoreCase(String nome);
}