package com.f1.crud.repository;     // criado (22/09/2026)

import com.f1.crud.domain.Escuderia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface EscuderiaRepository extends JpaRepository<Escuderia, Long> {

    // Busca escuderias por país de origem
    @Query("SELECT e FROM Escuderia e WHERE LOWER(e.paisOrigem) = LOWER(:pais)")
    List<Escuderia> buscarPorPaisOrigem(@Param("pais") String pais);
}






















/*package com.f1.crud.repository;

import com.f1.crud.domain.Escuderia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscuderiaRepository extends JpaRepository<Escuderia, Long> {
}*/