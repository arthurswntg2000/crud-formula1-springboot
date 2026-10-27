// Criado (22/09/2026); Atualizações (20/10/2026); (27/10/2026)
package com.f1.crud.service.impl;

import com.f1.crud.domain.Atividade;
import com.f1.crud.domain.Carro;
import com.f1.crud.domain.Escuderia;
import com.f1.crud.domain.Piloto;
import com.f1.crud.dto.CarroRequestDTO;
import com.f1.crud.dto.CarroResponseDTO;
import com.f1.crud.dto.PilotoResponseDTO;
import com.f1.crud.exception.NotFoundException;
import com.f1.crud.mapper.CarroMapper;
import com.f1.crud.mapper.PilotoMapper;
import com.f1.crud.repository.AtividadeRepository;
import com.f1.crud.repository.CarroRepository;
import com.f1.crud.repository.EscuderiaRepository;
import com.f1.crud.repository.PilotoRepository;
import com.f1.crud.service.CarroService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarroServiceImpl implements CarroService {

    private final CarroRepository carroRepository;
    private final EscuderiaRepository escuderiaRepository;
    private final PilotoRepository pilotoRepository;
    private final PilotoMapper pilotoMapper;
    private final AtividadeRepository atividadeRepository;
    private final CarroMapper carroMapper;

    public CarroServiceImpl(CarroRepository carroRepository,
                            EscuderiaRepository escuderiaRepository,
                            PilotoRepository pilotoRepository,
                            AtividadeRepository atividadeRepository,
                            CarroMapper carroMapper,
                            PilotoMapper pilotoMapper) {
        this.carroRepository = carroRepository;
        this.escuderiaRepository = escuderiaRepository;
        this.pilotoRepository = pilotoRepository;
        this.atividadeRepository = atividadeRepository;
        this.carroMapper = carroMapper;
        this.pilotoMapper = pilotoMapper;
    }

    @Override
    @Transactional
    public CarroResponseDTO criar(CarroRequestDTO dto) {
        Carro carro = carroMapper.toEntity(dto);

        if (dto.escuderiaId() != null) {
            Escuderia escuderia = escuderiaRepository.findById(dto.escuderiaId())
                    .orElseThrow(() -> new NotFoundException("Escuderia não encontrada com ID: " + dto.escuderiaId()));
            carro.setEscuderia(escuderia);
        }

        Carro salvo = carroRepository.save(carro);

        atividadeRepository.save(new Atividade("CARRO_CRIADO", "Novo carro registrado: " + salvo.getModelo()));
        return carroMapper.toDto(salvo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarroResponseDTO> listarTodos() {
        return carroMapper.toDtoList(carroRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CarroResponseDTO> listarPaginado(Pageable pageable) {
        return carroRepository.findAll(pageable).map(carroMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CarroResponseDTO> buscarPorModelo(String modelo, Pageable pageable) {
        return carroRepository.buscarPorModelo(modelo, pageable)
                .map(carroMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CarroResponseDTO> buscarPorAno(Integer ano, Pageable pageable) {
        return carroRepository.buscarPorAno(ano, pageable)
                .map(carroMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CarroResponseDTO> buscarPorMotor(String motor, Pageable pageable) {
        return carroRepository.buscarPorMotor(motor, pageable)
                .map(carroMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CarroResponseDTO> buscarPorEscuderia(String nomeEscuderia, Pageable pageable) {
        return carroRepository.buscarPorNomeEscuderia(nomeEscuderia, pageable)
                .map(carroMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public CarroResponseDTO buscarPorId(Long id) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Carro não encontrado com ID: " + id));

        CarroResponseDTO dtoBase = carroMapper.toDto(carro);

        // Procura os pilotos pertencentes à Escuderia do carro
        List<Piloto> pilotos = List.of();
        if (carro.getEscuderia() != null) {
            pilotos = pilotoRepository.findByEscuderiaId(carro.getEscuderia().getId());
        }

        // Converte os pilotos para PilotoResponseDTO usando o pilotoMapper
        Set<PilotoResponseDTO> pilotoDtos = pilotos.stream()
                .map(pilotoMapper::toDto)
                .collect(Collectors.toSet());

        // Retorna o DTO do carro com a coleção de pilotos devidamente preenchida
        return new CarroResponseDTO(
                dtoBase.id(),
                dtoBase.modelo(),
                dtoBase.motor(),
                dtoBase.ano(),
                dtoBase.urlFoto(),
                dtoBase.fichaTecnica(),
                dtoBase.nomeEscuderia(),
                pilotoDtos
        );
    }

    @Override
    @Transactional
    public CarroResponseDTO atualizar(Long id, CarroRequestDTO dto) {
        Carro carroExistente = carroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Carro não encontrado com ID: " + id));

        // Atualiza os campos básicos do carro
        carroMapper.updateEntityFromDto(dto, carroExistente);

        // Trata a Escuderia Vinculada
        if (dto.escuderiaId() != null) {
            Escuderia escuderia = escuderiaRepository.findById(dto.escuderiaId())
                    .orElseThrow(() -> new NotFoundException("Escuderia não encontrada com ID: " + dto.escuderiaId()));
            carroExistente.setEscuderia(escuderia);
        } else {
            carroExistente.setEscuderia(null);
        }

        // SINCRONIZAÇÃO DE PILOTOS: Se a lista de IDs vier preenchida, associa os novos. Se vier vazia/nula, limpa tudo!
        carroExistente.getPilotos().clear();
        
        if (dto.pilotoIds() != null && !dto.pilotoIds().isEmpty()) {
            List<Piloto> novosPilotos = pilotoRepository.findAllById(dto.pilotoIds());
            carroExistente.getPilotos().addAll(novosPilotos);
        }

        Carro atualizado = carroRepository.save(carroExistente);

        atividadeRepository.save(new Atividade("CARRO_ATUALIZADO", "Carro atualizado: " + atualizado.getModelo()));
        return buscarPorId(atualizado.getId());
    }

    @Override
    @Transactional
    public CarroResponseDTO reativar(Long id) {
        carroRepository.reativarPorId(id);

        Carro reativado = carroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Carro não encontrado com ID: " + id));

        atividadeRepository.save(new Atividade("CARRO_REATIVADO", "Carro reativado no sistema: " + reativado.getModelo()));

        return carroMapper.toDto(reativado);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Carro não encontrado com ID: " + id));

        carroRepository.deleteById(id);
        atividadeRepository.save(new Atividade("CARRO_DELETADO", "Carro removido do sistema: " + carro.getModelo()));
    }
}