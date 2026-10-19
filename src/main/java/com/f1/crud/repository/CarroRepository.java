// criado (22/09/2026); // Atualizações (02/10/2026); (19/10/2026)
package com.f1.crud.repository;     

import com.f1.crud.domain.Carro;
import org.springframework.data.domain.Page;    
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {

    // Criado em (19/10/2026)
    // Método para reativar um carro inativado pelo Soft Delete
    @Modifying
    @Transactional
    @Query(value = "UPDATE carro SET ativo = true WHERE id = :id", nativeQuery = true)
    void reativarPorId(@Param("id") Long id);

    // Busca o carro e traz a escuderia associada ignorando o filtro de ativo
    @Query(value = "SELECT * FROM carro WHERE id = :id", nativeQuery = true)
    Optional<Carro> findByIdIgnorandoStatus(@Param("id") Long id);

    // Carrega o carro, sua escuderia e os pilotos dessa escuderia em 1 único SELECT
    @EntityGraph(attributePaths = {"escuderia", "escuderia.pilotos"})
    @Query("SELECT c FROM Carro c WHERE c.id = :id")
    Optional<Carro> findByIdWithEscuderiaEPilotos(@Param("id") Long id);

    @Query("SELECT c FROM Carro c WHERE LOWER(c.modelo) LIKE LOWER(CONCAT('%', :modelo, '%'))")
    Page<Carro> buscarPorModelo(@Param("modelo") String modelo, Pageable pageable);

    @Query("SELECT c FROM Carro c WHERE LOWER(c.escuderia.nome) LIKE LOWER(CONCAT('%', :nomeEscuderia, '%'))")
    Page<Carro> buscarPorNomeEscuderia(@Param("nomeEscuderia") String nomeEscuderia, Pageable pageable);

    @Query("SELECT c FROM Carro c WHERE c.ano = :ano")
    Page<Carro> buscarPorAno(@Param("ano") Integer ano, Pageable pageable);

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