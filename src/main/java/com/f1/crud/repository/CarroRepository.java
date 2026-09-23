package com.f1.crud.repository;     // criado (22/09/2026)

import com.f1.crud.domain.Carro;
import org.springframework.data.domain.Page;    // criado (23/09/2026)
import org.springframework.data.domain.Pageable;    // criado (23/09/2026)
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {

    // Busca paginada por modelo;       Atualizado (23/09/2026)
    @Query("SELECT c FROM Carro c WHERE LOWER(c.modelo) LIKE LOWER(CONCAT('%', :modelo, '%'))")
    Page<Carro> buscarPorModelo(@Param("modelo") String modelo, Pageable pageable);

    // Busca paginada por escuderia     Atualizado (23/09/2026)
    @Query("SELECT c FROM Carro c WHERE LOWER(c.escuderia.nome) LIKE LOWER(CONCAT('%', :nomeEscuderia, '%'))")
    Page<Carro> buscarPorNomeEscuderia(@Param("nomeEscuderia") String nomeEscuderia, Pageable pageable);

    // Busca paginada por ano exato     Atualizado (23/09/2026)
    @Query("SELECT c FROM Carro c WHERE c.ano = :ano")
    Page<Carro> buscarPorAno(@Param("ano") Integer ano, Pageable pageable);

    // Busca paginada por motor     Atualizado (23/09/2026)
    @Query("SELECT c FROM Carro c WHERE LOWER(c.motor) LIKE LOWER(CONCAT('%', :motor, '%'))")
    Page<Carro> buscarPorMotor(@Param("motor") String motor, Pageable pageable);
}























/*package com.f1.crud.repository;

import com.f1.crud.domain.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
}*/