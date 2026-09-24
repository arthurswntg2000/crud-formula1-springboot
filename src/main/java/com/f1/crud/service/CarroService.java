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

    // Atualizado (23/09/2026)
    public Page<CarroRequestDTO> listarTodos(Pageable pageable) {
        return carroRepository.findAll(pageable).map(CarroRequestDTO::new);
    }

    public CarroRequestDTO buscarPorId(Long id) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Carro não encontrado pelo ID: " + id));  // Criado (22/09/2026)
        return new CarroRequestDTO(carro);
    }

    // criado (22/09/2026);     Atualizado (23/09/2026)
    public Page<CarroRequestDTO> buscarPorModelo(String modelo, Pageable pageable) {
        Page<Carro> carros = carroRepository.buscarPorModelo(modelo, pageable);
        if (carros.isEmpty()) {
            throw new NotFoundException("Nenhum carro encontrado para o modelo informado: " + modelo);
        }
        return carros.map(CarroRequestDTO::new);
    }

    // criado (22/09/2026);     Atualizado (23/09/2026)
    public Page<CarroRequestDTO> buscarPorNomeEscuderia(String nomeEscuderia, Pageable pageable) {
        Page<Carro> carros = carroRepository.buscarPorNomeEscuderia(nomeEscuderia, pageable);
        if (carros.isEmpty()) {
            throw new NotFoundException("Nenhum carro encontrado para a escuderia informada: " + nomeEscuderia);
        }
        return carros.map(CarroRequestDTO::new);
    }

    // criado (22/09/2026);     Atualizado (23/09/2026)
    public Page<CarroRequestDTO> buscarPorAno(Integer ano, Pageable pageable) {
        Page<Carro> carros = carroRepository.buscarPorAno(ano, pageable);
        if (carros.isEmpty()) {
            throw new NotFoundException("Nenhum carro encontrado para o ano informado: " + ano);
        }
        return carros.map(CarroRequestDTO::new);
    }

    // criado (22/09/2026);     Atualizado (23/09/2026)
    public Page<CarroRequestDTO> buscarPorMotor(String motor, Pageable pageable) {
        Page<Carro> carros = carroRepository.buscarPorMotor(motor, pageable);
        if (carros.isEmpty()) {
            throw new NotFoundException("Nenhum carro encontrado para o motor informado: " + motor);
        }
        return carros.map(CarroRequestDTO::new);
    }

    // Atualizado (23/09/2026)
    public CarroRequestDTO salvar(Carro carro, Long escuderiaId) {
        Escuderia escuderia = escuderiaRepository.findById(escuderiaId)
                .orElseThrow(() -> new NotFoundException("Escuderia não encontrada pelo ID: " + escuderiaId));  // Criado (22/09/2026)
        carro.setEscuderia(escuderia);
        Carro carroSalvo = carroRepository.save(carro);     //criado (23/09/2026)
        return new CarroRequestDTO(carroSalvo);
    }

    // Atualizado (23/09/2026)
    public CarroRequestDTO atualizar(Long id, Carro carroAtualizado) {
        Carro carro = carroRepository.findById(id)      // criado (23/09/2026)
                .orElseThrow(() -> new NotFoundException("Carro não encontrado pelo ID: " + id));     // criado (23/09/2026)
        carro.setModelo(carroAtualizado.getModelo());
        carro.setMotor(carroAtualizado.getMotor());
        carro.setAno(carroAtualizado.getAno());
        Carro carroSalvo = carroRepository.save(carro);
        return new CarroRequestDTO(carroSalvo);
    }

    public void deletar(Long id) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Carro não encontrado pelo ID: " + id));   // criado (23/09/2026)
        carroRepository.delete(carro);
    }
}