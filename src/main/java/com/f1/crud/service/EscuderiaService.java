package com.f1.crud.service;

import com.f1.crud.domain.Escuderia;
import com.f1.crud.dto.EscuderiaRequestDTO;
import com.f1.crud.dto.EscuderiaResponseDTO;
import com.f1.crud.exception.NotFoundException;     // Criado (22/09/2026)
import com.f1.crud.mapper.EscuderiaMapper;  // Criado (22/09/2026)
import com.f1.crud.repository.EscuderiaRepository;
import org.springframework.data.domain.Page;    // Criado (22/09/2026)
import org.springframework.data.domain.Pageable;    // Criado (22/09/2026)
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
public class EscuderiaService {

    private final EscuderiaRepository repository;
    private final EscuderiaMapper escuderiaMapper;

    public EscuderiaService(EscuderiaRepository escuderiaRepository, EscuderiaMapper escuderiaMapper) {
        this.escuderiaRepository = escuderiaRepository;
        this.escuderiaMapper = escuderiaMapper;
    }

    @Transactional
    public EscuderiaResponseDTO criar(EscuderiaRequestDTO dto) {
        Escuderia escuderia = escuderiaMapper.toEntity(dto);
        Escuderia salva = escuderiaRepository.save(escuderia);
        return escuderiaMapper.toDto(salva);
    }

    @Transactional(readOnly = true)
    public List<EscuderiaResponseDTO> listarTodos() {
        return escuderiaMapper.toDtoList(escuderiaRepository.findAll());
    }

    @Transactional(readOnly = true)
    public Page<EscuderiaResponseDTO> listarPaginado(Pageable pageable) {
        return escuderiaRepository.findAll(pageable).map(escuderiaMapper::toDto);
    }

    @Transactional(readOnly = true)
    public EscuderiaResponseDTO buscarPorId(Long id) {
        Escuderia escuderia = escuderiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Escuderia não encontrada com ID: " + id));
        return escuderiaMapper.toDto(escuderia);
    }
    
    @Transactional
    public EscuderiaResponseDTO atualizar(Long id, EscuderiaRequestDTO dto) {
        Escuderia escuderiaExistente = escuderiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Escuderia não encontrada com ID: " + id));

        escuderiaMapper.updateEntityFromDto(dto, escuderiaExistente);

        Escuderia atualizada = escuderiaRepository.save(escuderiaExistente);
        return escuderiaMapper.toDto(atualizada);
    }

    @Transactional
    public void deletar(Long id) {
        if (!escuderiaRepository.existsById(id)) {
            throw new NotFoundException("Escuderia não encontrada com ID: " + id);
        }
        escuderiaRepository.deleteById(id);
    }
}