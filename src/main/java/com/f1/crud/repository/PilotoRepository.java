package com.f1.crud.repository;    // Criado (23/09/2026)   // Atualizado (24/09/2026)

import com.f1.crud.domain.Piloto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PilotoRepository extends JpaRepository<Piloto, Long> {

    // Busca paginada por parte do nome do piloto (case-insensitive)
    @Query("SELECT p FROM Piloto p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    Page<Piloto> buscarPorNome(@Param("nome") String nome, Pageable pageable);

    // Busca paginada por nacionalidade
    @Query("SELECT p FROM Piloto p WHERE LOWER(p.nacionalidade) LIKE LOWER(CONCAT('%', :nacionalidade, '%'))")
    Page<Piloto> buscarPorNacionalidade(@Param("nacionalidade") String nacionalidade, Pageable pageable);
}