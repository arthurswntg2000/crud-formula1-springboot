// Criado (23/09/2026)   // Atualizações (24/09/2026); (02/10/2026); (19/10/2026); (20/10/2026); (27/10/2026)
package com.f1.crud.repository;    

import com.f1.crud.domain.Piloto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PilotoRepository extends JpaRepository<Piloto, Long> {

    // Método para reativar um piloto inativado pelo Soft Delete
    @Modifying
    @Transactional
    @Query(value = "UPDATE tb_piloto SET ativo = true WHERE id = :id", nativeQuery = true)
    void reativarPorId(@Param("id") Long id);

    // Busca JPQL que ignora o filtro soft-delete do Hibernate
    @Query("SELECT p FROM Piloto p LEFT JOIN FETCH p.escuderias WHERE p.id = :id")
    Optional<Piloto> findByIdIgnorandoStatus(@Param("id") Long id);

    // Carrega o piloto junto com suas escuderias para a tela de Perfil em 1 único SELECT
    @EntityGraph(attributePaths = {"escuderias"})
    @Query("SELECT p FROM Piloto p WHERE p.id = :id")
    Optional<Piloto> findByIdWithEscuderias(@Param("id") Long id);

    // Busca pilotos associados a uma escuderia pelo ID da escuderia
    @Query("SELECT DISTINCT p FROM Piloto p JOIN p.escuderias e WHERE e.id = :escuderiaId")
    List<Piloto> findByEscuderiaId(@Param("escuderiaId") Long escuderiaId);

    @Query("SELECT p FROM Piloto p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    Page<Piloto> buscarPorNome(@Param("nome") String nome, Pageable pageable);

    @Query("SELECT p FROM Piloto p WHERE LOWER(p.nacionalidade) LIKE LOWER(CONCAT('%', :nacionalidade, '%'))")
    Page<Piloto> buscarPorNacionalidade(@Param("nacionalidade") String nacionalidade, Pageable pageable);
}