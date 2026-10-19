// Criado (19/10/2026)
package com.f1.crud.repository;

import com.f1.crud.domain.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
    // Retorna as 10 atividades mais recentes ordenadas por data
    List<Atividade> findTop10ByOrderByTimestampDesc();
}