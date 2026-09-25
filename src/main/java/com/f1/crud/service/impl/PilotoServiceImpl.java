// Atualizado (25/09/2026)
package com.f1.crud.service.impl;

import com.f1.crud.domain.Escuderia;
import com.f1.crud.domain.Piloto;
import com.f1.crud.dto.PilotoRequestDTO;
import com.f1.crud.dto.PilotoResponseDTO;
import com.f1.crud.exception.NotFoundException;
import com.f1.crud.mapper.PilotoMapper;
import com.f1.crud.repository.EscuderiaRepository;
import com.f1.crud.repository.PilotoRepository;
import com.f1.crud.service.PilotoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
public class PilotoServiceImpl implements PilotoService {

    private final PilotoRepository pilotoRepository;
    private final EscuderiaRepository escuderiaRepository;
    private final PilotoMapper pilotoMapper;

    public PilotoServiceImpl(PilotoRepository pilotoRepository,
                             EscuderiaRepository escuderiaRepository,
                             PilotoMapper pilotoMapper) {
        this.pilotoRepository = pilotoRepository;
        this.escuderiaRepository = escuderiaRepository;
        this.pilotoMapper = pilotoMapper;
    }

    @Override
    @Transactional
    public PilotoResponseDTO criar(PilotoRequestDTO dto) {
        Piloto piloto = pilotoMapper.toEntity(dto);

        if (dto.escuderiasIds() != null && !dto.escuderiasIds().isEmpty()) {
            List<Escuderia> escuderias = escuderiaRepository.findAllById(dto.escuderiasIds());
            piloto.setEscuderias(new HashSet<>(escuderias));
        }

        Piloto salvo = pilotoRepository.save(piloto);
        return pilotoMapper.toDto(salvo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PilotoResponseDTO> listarTodos() {
        return pilotoMapper.toDtoList(pilotoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PilotoResponseDTO> listarPaginado(Pageable pageable) {
        return pilotoRepository.findAll(pageable).map(pilotoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public PilotoResponseDTO buscarPorId(Long id) {
        Piloto piloto = pilotoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Piloto não encontrado com ID: " + id));
        return pilotoMapper.toDto(piloto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PilotoResponseDTO> buscarPorNome(String nome, Pageable pageable) {
        return pilotoRepository.buscarPorNome(nome, pageable).map(pilotoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PilotoResponseDTO> buscarPorNacionalidade(String nacionalidade, Pageable pageable) {
        return pilotoRepository.buscarPorNacionalidade(nacionalidade, pageable).map(pilotoMapper::toDto);
    }

    @Override
    @Transactional
    public PilotoResponseDTO atualizar(Long id, PilotoRequestDTO dto) {
        Piloto pilotoExistente = pilotoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Piloto não encontrado com ID: " + id));

        pilotoMapper.updateEntityFromDto(dto, pilotoExistente);

        if (dto.escuderiasIds() != null) {
            List<Escuderia> escuderias = escuderiaRepository.findAllById(dto.escuderiasIds());
            pilotoExistente.setEscuderias(new HashSet<>(escuderias));
        }

        Piloto atualizado = pilotoRepository.save(pilotoExistente);
        return pilotoMapper.toDto(atualizado);
    }

    @Override
    @Transactional
    public PilotoResponseDTO adicionarEscuderia(Long pilotoId, Long escuderiaId) {
        Piloto piloto = pilotoRepository.findById(pilotoId)
                .orElseThrow(() -> new NotFoundException("Piloto não encontrado com ID: " + pilotoId));

        Escuderia escuderia = escuderiaRepository.findById(escuderiaId)
                .orElseThrow(() -> new NotFoundException("Escuderia não encontrada com ID: " + escuderiaId));

        piloto.getEscuderias().add(escuderia);

        Piloto atualizado = pilotoRepository.save(piloto);
        return pilotoMapper.toDto(atualizado);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        if (!pilotoRepository.existsById(id)) {
            throw new NotFoundException("Piloto não encontrado com ID: " + id);
        }
        pilotoRepository.deleteById(id);
    }
}