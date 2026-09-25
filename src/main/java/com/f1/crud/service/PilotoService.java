// Atualizado (25/09/2026)
package com.f1.crud.service;

import com.f1.crud.dto.PilotoRequestDTO;
import com.f1.crud.dto.PilotoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PilotoService {
    PilotoResponseDTO criar(PilotoRequestDTO dto);
    List<PilotoResponseDTO> listarTodos();
    Page<PilotoResponseDTO> listarPaginado(Pageable pageable);
    PilotoResponseDTO buscarPorId(Long id);
    Page<PilotoResponseDTO> buscarPorNome(String nome, Pageable pageable);
    Page<PilotoResponseDTO> buscarPorNacionalidade(String nacionalidade, Pageable pageable);
    PilotoResponseDTO atualizar(Long id, PilotoRequestDTO dto);
    PilotoResponseDTO adicionarEscuderia(Long pilotoId, Long escuderiaId);
    void deletar(Long id);
}