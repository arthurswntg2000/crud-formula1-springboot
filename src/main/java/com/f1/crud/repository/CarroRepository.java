// criado (22/09/2026); // Atualizado (02/10/2026)
package com.f1.crud.repository;     

import com.f1.crud.domain.Carro;
import org.springframework.data.domain.Page;    
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {

    // Carrega o carro, sua escuderia e os pilotos dessa escuderia em 1 único SELECT
    @EntityGraph(attributePaths = {"escuderia", "escuderia.pilotos"})
    Optional<Carro> findByIdWithEscuderiaEPilotos(Long id);

    // Busca paginada por modelo para a barra de busca da tela de Listagem
    Page<Carro> findByModeloContainingIgnoreCase(String modelo, Pageable pageable);
}























/*package com.f1.crud.repository;

import com.f1.crud.domain.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
}*/