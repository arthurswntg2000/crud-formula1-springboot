// Atualizado (25/09/2026)
package com.f1.crud.service;

import com.f1.crud.dto.EscuderiaRequestDTO;
import com.f1.crud.dto.EscuderiaResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EscuderiaService {
    EscuderiaResponseDTO criar(EscuderiaRequestDTO dto);
    List<EscuderiaResponseDTO> listarTodos();
    Page<EscuderiaResponseDTO> listarPaginado(Pageable pageable);
    EscuderiaResponseDTO buscarPorId(Long id);
    EscuderiaResponseDTO atualizar(Long id, EscuderiaRequestDTO dto);
    void deletar(Long id);
}