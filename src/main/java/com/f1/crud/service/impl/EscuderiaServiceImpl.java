// Atualizado (25/09/2026)
package com.f1.crud.service.impl;

import com.f1.crud.domain.Atividade;
import com.f1.crud.domain.Escuderia;
import com.f1.crud.dto.EscuderiaRequestDTO;
import com.f1.crud.dto.EscuderiaResponseDTO;
import com.f1.crud.exception.NotFoundException;
import com.f1.crud.mapper.EscuderiaMapper;
import com.f1.crud.repository.AtividadeRepository;
import com.f1.crud.repository.EscuderiaRepository;
import com.f1.crud.service.EscuderiaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EscuderiaServiceImpl implements EscuderiaService {

    private final EscuderiaRepository escuderiaRepository;
    private final AtividadeRepository atividadeRepository;
    private final EscuderiaMapper escuderiaMapper;

    public EscuderiaServiceImpl(EscuderiaRepository escuderiaRepository, 
                                AtividadeRepository atividadeRepository, 
                                EscuderiaMapper escuderiaMapper) {
        this.escuderiaRepository = escuderiaRepository;
        this.atividadeRepository = atividadeRepository;
        this.escuderiaMapper = escuderiaMapper;
    }

    @Override
    @Transactional
    public EscuderiaResponseDTO criar(EscuderiaRequestDTO dto) {
        Escuderia escuderia = escuderiaMapper.toEntity(dto);
        Escuderia salva = escuderiaRepository.save(escuderia);
        atividadeRepository.save(new Atividade("ESCUDERIA_CRIADA", "Nova escuderia registrada: " + salva.getNome()));
        return escuderiaMapper.toDto(salva);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EscuderiaResponseDTO> listarTodos() {
        return escuderiaMapper.toDtoList(escuderiaRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EscuderiaResponseDTO> listarPaginado(Pageable pageable) {
        return escuderiaRepository.findAll(pageable).map(escuderiaMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public EscuderiaResponseDTO buscarPorId(Long id) {
        Escuderia escuderia = escuderiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Escuderia não encontrada com ID: " + id));
        return escuderiaMapper.toDto(escuderia);
    }

    // criado (02/10/2026)
    @Override
    @Transactional(readOnly = true)
    public Page<EscuderiaResponseDTO> buscarPorNome(String nome, Pageable pageable) {
        return escuderiaRepository.buscarPorNome(nome, pageable).map(escuderiaMapper::toDto);
    }

    @Override
    @Transactional
    public EscuderiaResponseDTO atualizar(Long id, EscuderiaRequestDTO dto) {
        Escuderia escuderiaExistente = escuderiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Escuderia não encontrada com ID: " + id));

        escuderiaMapper.updateEntityFromDto(dto, escuderiaExistente);

        Escuderia atualizada = escuderiaRepository.save(escuderiaExistente);
        return escuderiaMapper.toDto(atualizada);
    }

    @Override
    @Transactional
    public EscuderiaResponseDTO reativar(Long id) {
        if (!escuderiaRepository.existsById(id)) {
            throw new NotFoundException("Escuderia não encontrada com ID: " + id);
        }

        escuderiaRepository.reativarPorId(id);

        Escuderia reativada = escuderiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Erro ao carregar escuderia reativada."));

        atividadeRepository.save(new Atividade("ESCUDERIA_REATIVADA", "Escuderia reativada no sistema: " + reativada.getNome()));

        return escuderiaMapper.toDto(reativada);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        Escuderia escuderia = escuderiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Escuderia não encontrada com ID: " + id));

        escuderiaRepository.deleteById(id);
        atividadeRepository.save(new Atividade("ESCUDERIA_DELETADA", "Escuderia removida do sistema: " + escuderia.getNome()));
    }
}