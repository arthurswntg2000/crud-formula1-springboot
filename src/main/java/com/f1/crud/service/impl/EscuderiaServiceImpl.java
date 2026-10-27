// Criado (22/09/2026); Atualizações (25/09/2026); (20/10/2026); (27/10/2026)
package com.f1.crud.service.impl;

import com.f1.crud.domain.Atividade;
import com.f1.crud.domain.Carro;
import com.f1.crud.domain.Escuderia;
import com.f1.crud.domain.Piloto;
import com.f1.crud.dto.CarroResponseDTO;
import com.f1.crud.dto.EscuderiaRequestDTO;
import com.f1.crud.dto.EscuderiaResponseDTO;
import com.f1.crud.dto.PilotoResponseDTO;
import com.f1.crud.exception.NotFoundException;
import com.f1.crud.mapper.CarroMapper;
import com.f1.crud.mapper.EscuderiaMapper;
import com.f1.crud.mapper.PilotoMapper;
import com.f1.crud.repository.AtividadeRepository;
import com.f1.crud.repository.CarroRepository;
import com.f1.crud.repository.EscuderiaRepository;
import com.f1.crud.repository.PilotoRepository;
import com.f1.crud.service.EscuderiaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EscuderiaServiceImpl implements EscuderiaService {

    private final EscuderiaRepository escuderiaRepository;
    private final PilotoRepository pilotoRepository;
    private final CarroRepository carroRepository;
    private final AtividadeRepository atividadeRepository;
    private final EscuderiaMapper escuderiaMapper;
    private final CarroMapper carroMapper;
    private final PilotoMapper pilotoMapper;

    public EscuderiaServiceImpl(EscuderiaRepository escuderiaRepository,
                                PilotoRepository pilotoRepository,
                                CarroRepository carroRepository,
                                AtividadeRepository atividadeRepository, 
                                EscuderiaMapper escuderiaMapper,
                                CarroMapper carroMapper,
                                PilotoMapper pilotoMapper) {
        this.escuderiaRepository = escuderiaRepository;
        this.pilotoRepository = pilotoRepository;
        this.carroRepository = carroRepository;
        this.atividadeRepository = atividadeRepository;
        this.escuderiaMapper = escuderiaMapper;
        this.carroMapper = carroMapper;
        this.pilotoMapper = pilotoMapper;
    }

    @Override
    @Transactional
    public EscuderiaResponseDTO criar(EscuderiaRequestDTO dto) {
        Escuderia escuderia = escuderiaMapper.toEntity(dto);
        Escuderia salva = escuderiaRepository.save(escuderia);

        if (dto.pilotoIds() != null && !dto.pilotoIds().isEmpty()) {
            List<Piloto> pilotos = pilotoRepository.findAllById(dto.pilotoIds());
            for (Piloto p : pilotos) {
                p.getEscuderias().add(salva);
            }
            pilotoRepository.saveAll(pilotos);
        }

        if (dto.carroIds() != null && !dto.carroIds().isEmpty()) {
            List<Carro> carros = carroRepository.findAllById(dto.carroIds());
            for (Carro c : carros) {
                c.setEscuderia(salva);
            }
            carroRepository.saveAll(carros);
        }

        atividadeRepository.save(new Atividade("ESCUDERIA_CRIADA", "Nova escuderia registrada: " + salva.getNome()));
        return buscarPorId(salva.getId());
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

        // Busca os carros e pilotos associados a esta escuderia
        List<Carro> carros = carroRepository.findByEscuderiaId(id);
        Set<CarroResponseDTO> carroDtos = carros.stream()
                .map(carroMapper::toDto)
                .collect(Collectors.toSet());

        List<Piloto> pilotos = pilotoRepository.findByEscuderiaId(id);
        Set<PilotoResponseDTO> pilotoDtos = pilotos.stream()
                .map(pilotoMapper::toDto)
                .collect(Collectors.toSet());

        EscuderiaResponseDTO dtoBase = escuderiaMapper.toDto(escuderia);

        // Ordem exata: fundador (String) antes de titulos (Integer)
        return new EscuderiaResponseDTO(
                dtoBase.id(),
                dtoBase.nome(),
                dtoBase.paisOrigem(),
                dtoBase.dataFundacao(),
                dtoBase.fundador(),   // String
                dtoBase.titulos(),    // Integer
                dtoBase.urlLogo(),
                dtoBase.biografia(),
                carroDtos,
                pilotoDtos
        );
    }

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

        // Associa os Pilotos
        if (dto.pilotoIds() != null) {
            List<Piloto> pilotos = pilotoRepository.findAllById(dto.pilotoIds());
            for (Piloto p : pilotos) {
                if (!p.getEscuderias().contains(atualizada)) {
                    p.getEscuderias().add(atualizada);
                }
            }
            pilotoRepository.saveAll(pilotos);
        }

        // Associa os Carros
        if (dto.carroIds() != null) {
            List<Carro> carros = carroRepository.findAllById(dto.carroIds());
            for (Carro c : carros) {
                c.setEscuderia(atualizada);
            }
            carroRepository.saveAll(carros);
        }

        atividadeRepository.save(new Atividade("ESCUDERIA_ATUALIZADA", "Escuderia atualizada: " + atualizada.getNome()));
    
        // Retorna o DTO com os carros e pilotos montados
        return buscarPorId(atualizada.getId());
    }

    @Override
    @Transactional
    public EscuderiaResponseDTO reativar(Long id) {
        escuderiaRepository.reativarPorId(id);

        Escuderia reativada = escuderiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Escuderia não encontrada com ID: " + id));

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