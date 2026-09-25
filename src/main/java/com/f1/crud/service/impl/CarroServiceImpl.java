package com.f1.crud.service.impl;

import com.f1.crud.domain.Carro;
import com.f1.crud.domain.Escuderia;
import com.f1.crud.dto.CarroRequestDTO;
import com.f1.crud.dto.CarroResponseDTO;
import com.f1.crud.exception.NotFoundException;
import com.f1.crud.mapper.CarroMapper;
import com.f1.crud.repository.CarroRepository;
import com.f1.crud.repository.EscuderiaRepository;
import com.f1.crud.service.CarroService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarroServiceImpl implements CarroService {

    private final CarroRepository carroRepository;
    private final EscuderiaRepository escuderiaRepository;
    private final CarroMapper carroMapper;

    public CarroServiceImpl(CarroRepository carroRepository,
                            EscuderiaRepository escuderiaRepository,
                            CarroMapper carroMapper) {
        this.carroRepository = carroRepository;
        this.escuderiaRepository = escuderiaRepository;
        this.carroMapper = carroMapper;
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
    public CarroResponseDTO buscarPorId(Long id) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Carro não encontrado com ID: " + id));
        return carroMapper.toDto(carro);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CarroResponseDTO> buscarPorModelo(String modelo, Pageable pageable) {
        return carroRepository.buscarPorModelo(modelo, pageable).map(carroMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CarroResponseDTO> buscarPorEscuderia(String nomeEscuderia, Pageable pageable) {
        return carroRepository.buscarPorNomeEscuderia(nomeEscuderia, pageable).map(carroMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CarroResponseDTO> buscarPorAno(Integer ano, Pageable pageable) {
        return carroRepository.buscarPorAno(ano, pageable).map(carroMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CarroResponseDTO> buscarPorMotor(String motor, Pageable pageable) {
        return carroRepository.buscarPorMotor(motor, pageable).map(carroMapper::toDto);
    }

    @Override
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

    @Override
    @Transactional
    public void deletar(Long id) {
        if (!carroRepository.existsById(id)) {
            throw new NotFoundException("Carro não encontrado com ID: " + id);
        }
        carroRepository.deleteById(id);
    }
}