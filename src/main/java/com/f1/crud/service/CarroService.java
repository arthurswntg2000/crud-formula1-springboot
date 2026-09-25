// Atualizado (25/09/2026)
package com.f1.crud.service;

import com.f1.crud.dto.CarroRequestDTO;
import com.f1.crud.dto.CarroResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarroService {
    CarroResponseDTO criar(CarroRequestDTO dto);
    List<CarroResponseDTO> listarTodos();
    Page<CarroResponseDTO> listarPaginado(Pageable pageable);
    CarroResponseDTO buscarPorId(Long id);
    Page<CarroResponseDTO> buscarPorModelo(String modelo, Pageable pageable);
    Page<CarroResponseDTO> buscarPorEscuderia(String nomeEscuderia, Pageable pageable);
    Page<CarroResponseDTO> buscarPorAno(Integer ano, Pageable pageable);
    Page<CarroResponseDTO> buscarPorMotor(String motor, Pageable pageable);
    CarroResponseDTO atualizar(Long id, CarroRequestDTO dto);
    void deletar(Long id);
}