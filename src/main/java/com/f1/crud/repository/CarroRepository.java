package com.f1.crud.repository;     // criado (22/09/2026)

import com.f1.crud.domain.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {

    // Busca carros pelo nome do modelo (ignorando maiúsculas e minúsculas)
    @Query("SELECT c FROM Carro c WHERE LOWER(c.modelo) LIKE LOWER(CONCAT('%', :modelo, '%'))")
    List<Carro> buscarPorModelo(@Param("modelo") String modelo);

    // Busca carros pelo nome da escuderia
    @Query("SELECT c FROM Carro c WHERE LOWER(c.escuderia.nome) LIKE LOWER(CONCAT('%', :nomeEscuderia, '%'))")
    List<Carro> buscarPorNomeEscuderia(@Param("nomeEscuderia") String nomeEscuderia);

    // Busca carros pelo ano de fabricação
    @Query("SELECT c FROM Carro c WHERE c.ano >= :ano")
    List<Carro> buscarPorAnoMaiorOuIgual(@Param("ano") Integer ano);

    // Busca carros pelo motor
    @Query("SELECT c FROM Carro c WHERE LOWER(c.motor) LIKE LOWER(CONCAT('%', :motor, '%'))")
    List<Carro> buscarPorMotor(@Param("motor") String motor);
}























/*package com.f1.crud.repository;

import com.f1.crud.domain.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
}*/