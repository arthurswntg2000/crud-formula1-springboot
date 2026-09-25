package com.f1.crud.service;

import com.f1.crud.domain.Carro;
import com.f1.crud.domain.Escuderia;
import com.f1.crud.dto.CarroRequestDTO; //  criado (24/09/2026)
import com.f1.crud.dto.CarroResponseDTO;   //  criado (24/09/2026)
import com.f1.crud.exception.NotFoundException;  // Criado (22/09/2026)
import com.f1.crud.repository.CarroRepository;
import com.f1.crud.repository.EscuderiaRepository;
import com.f1.crud.mapper.CarroMapper;  //  criado (24/09/2026)
import org.springframework.data.domain.Page;        // criado (23/09/2026)
import org.springframework.data.domain.Pageable;        // criado (23/09/2026)
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarroService {

    private final CarroRepository carroRepository;
    private final EscuderiaRepository escuderiaRepository;
    private final CarroMapper carroMapper;

    // Atualizado (24/09/2026)
    public CarroService(CarroRepository carroRepository, EscuderiaRepository escuderiaRepository, CarroMapper carroMapper) {
        this.carroRepository = carroRepository;
        this.escuderiaRepository = escuderiaRepository;
        this.carroMapper = carroMapper;
    }

    // Atualizado (24/09/2026)
    @Transactional
    public CarroResponseDTO criar(CarroRequestDTO dto) {
        Carro carro = carroMapper.toEntity(dto);

        if (dto.escuderiaId() != null) {
            Escuderia escuderia = escuderiaRepository.findById(dto.escuderiaId())
                    .orElseThrow(() -> new NotFoundException("Escuderia não encontrada com ID: " + dto.escuderiaId()));
            carro.setEscuderia(escuderia);
        }

        Carro salvo = carroRepository.save(carro);
        return carroMapper.toDto(salvo);
    }

    // Criado (24/09/2026)
    @Transactional(readOnly = true)
    public List<CarroResponseDTO> listarTodos() {
        List<Carro> carros = carroRepository.findAll();
        return carroMapper.toDtoList(carros);
    }

    // Criado (24/09/2026)
    @Transactional(readOnly = true)
    public Page<CarroResponseDTO> listarPaginado(Pageable pageable) {
        return carroRepository.findAll(pageable).map(carroMapper::toDto);
    }

    // --- Buscas customizadas paginadas ---

    // criado (22/09/2026);     Atualizado (24/09/2026)

    @Transactional(readOnly = true)
    public Page<CarroResponseDTO> buscarPorModelo(String modelo, Pageable pageable) {
        return carroRepository.buscarPorModelo(modelo, pageable).map(carroMapper::toDto);
    }

    // criado (22/09/2026);     Atualizado (24/09/2026)

    @Transactional(readOnly = true)
    public Page<CarroResponseDTO> buscarPorEscuderia(String nomeEscuderia, Pageable pageable) {
        return carroRepository.buscarPorNomeEscuderia(nomeEscuderia, pageable).map(carroMapper::toDto);
    }

    // criado (22/09/2026);     Atualizado (24/09/2026)

    @Transactional(readOnly = true)
    public Page<CarroResponseDTO> buscarPorAno(Integer ano, Pageable pageable) {
        return carroRepository.buscarPorAno(ano, pageable).map(carroMapper::toDto);
    }

    // criado (22/09/2026);     Atualizado (24/09/2026)

    @Transactional(readOnly = true)
    public Page<CarroResponseDTO> buscarPorMotor(String motor, Pageable pageable) {
        return carroRepository.buscarPorMotor(motor, pageable).map(carroMapper::toDto);
    }


    @Transactional
    public CarroResponseDTO atualizar(Long id, CarroRequestDTO dto) {
        Carro carroExistente = carroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Carro não encontrado com ID: " + id));

        carroMapper.updateEntityFromDto(dto, carroExistente);

        if (dto.escuderiaId() != null) {
            Escuderia escuderia = escuderiaRepository.findById(dto.escuderiaId())
                    .orElseThrow(() -> new NotFoundException("Escuderia não encontrada com ID: " + dto.escuderiaId()));
            carroExistente.setEscuderia(escuderia);
        } else {
            carroExistente.setEscuderia(null);
        }

        Carro atualizado = carroRepository.save(carroExistente);
        return carroMapper.toDto(atualizado);
    }

    @Transactional
    public void deletar(Long id) {
        if (!carroRepository.existsById(id)) {
            throw new NotFoundException("Carro não encontrado com ID: " + id);
        }
        carroRepository.deleteById(id);
    }
}