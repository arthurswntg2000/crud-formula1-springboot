package com.f1.crud.repository;     // criado (22/09/2026)

import com.f1.crud.domain.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {

    // JPQL: Buscar carros por parte do nome do modelo (ignorando maiúsculas/minúsculas)
    @Query("SELECT c FROM Carro c WHERE LOWER(c.modelo) LIKE LOWER(CONCAT('%', :modelo, '%'))")
    List<Carro> buscarPorModelo(@Param("modelo") String modelo);

    // JPQL: Buscar carros pelo nome da escuderia associada
    @Query("SELECT c FROM Carro c WHERE LOWER(c.escuderia.nome) LIKE LOWER(CONCAT('%', :nomeEscuderia, '%'))")
    List<Carro> buscarPorNomeEscuderia(@Param("nomeEscuderia") String nomeEscuderia);
}























/*package com.f1.crud.repository;

import com.f1.crud.domain.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
}*/