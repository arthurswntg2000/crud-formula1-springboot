package com.f1.crud.service;

import com.f1.crud.domain.Carro;
import com.f1.crud.domain.Escuderia;
import com.f1.crud.DTO.CarroDTO;    // criado (23/09/2026)
import com.f1.crud.exception.NotFoundException;  // Criado (22/09/2026)
import com.f1.crud.repository.CarroRepository;
import com.f1.crud.repository.EscuderiaRepository;
import org.springframework.data.domain.Page;        // criado (23/09/2026)
import org.springframework.data.domain.Pageable;        // criado (23/09/2026)
import org.springframework.stereotype.Service;

@Service
public class CarroService {

    private final CarroRepository carroRepository;
    private final EscuderiaRepository escuderiaRepository;

    public CarroService(CarroRepository carroRepository, EscuderiaRepository escuderiaRepository) {
        this.carroRepository = carroRepository;
        this.escuderiaRepository = escuderiaRepository;
    }

    // Atualizado (23/09/2026)
    public Page<CarroDTO> listarTodos(Pageable pageable) {
        return carroRepository.findAll(pageable).map(CarroDTO::new);
    }

    public CarroDTO buscarPorId(Long id) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Carro não encontrado pelo ID: " + id));  // Criado (22/09/2026)
        return new CarroDTO(carro);
    }

    // criado (22/09/2026);     Atualizado (23/09/2026)
    public Page<CarroDTO> buscarPorModelo(String modelo, Pageable pageable) {
        Page<Carro> carros = carroRepository.buscarPorModelo(modelo, pageable);
        if (carros.isEmpty()) {
            throw new NotFoundException("Nenhum carro encontrado para o modelo informado: " + modelo);
        }
        return carros.map(CarroDTO::new);
    }

    // criado (22/09/2026);     Atualizado (23/09/2026)
    public Page<CarroDTO> buscarPorNomeEscuderia(String nomeEscuderia, Pageable pageable) {
        Page<Carro> carros = carroRepository.buscarPorNomeEscuderia(nomeEscuderia, pageable);
        if (carros.isEmpty()) {
            throw new NotFoundException("Nenhum carro encontrado para a escuderia informada: " + nomeEscuderia);
        }
        return carros.map(CarroDTO::new);
    }

    // criado (22/09/2026);     Atualizado (23/09/2026)
    public Page<CarroDTO> buscarPorAno(Integer ano, Pageable pageable) {
        Page<Carro> carros = carroRepository.buscarPorAno(ano, pageable);
        if (carros.isEmpty()) {
            throw new NotFoundException("Nenhum carro encontrado para o ano informado: " + ano);
        }
        return carros.map(CarroDTO::new);
    }

    // criado (22/09/2026);     Atualizado (23/09/2026)
    public Page<CarroDTO> buscarPorMotor(String motor, Pageable pageable) {
        Page<Carro> carros = carroRepository.buscarPorMotor(motor, pageable);
        if (carros.isEmpty()) {
            throw new NotFoundException("Nenhum carro encontrado para o motor informado: " + motor);
        }
        return carros.map(CarroDTO::new);
    }

    // Atualizado (23/09/2026)
    public CarroDTO salvar(Carro carro, Long escuderiaId) {
        Escuderia escuderia = escuderiaRepository.findById(escuderiaId)
                .orElseThrow(() -> new NotFoundException("Escuderia não encontrada pelo ID: " + escuderiaId));  // Criado (22/09/2026)
        carro.setEscuderia(escuderia);
        Carro carroSalvo = carroRepository.save(carro);     //criado (23/09/2026)
        return new CarroDTO(carroSalvo);
    }

    // Atualizado (23/09/2026)
    public CarroDTO atualizar(Long id, Carro carroAtualizado) {
        Carro carro = carroRepository.findById(id)      // criado (23/09/2026)
                .orElseThrow(() -> new NotFoundException("Carro não encontrado pelo ID: " + id));     // criado (23/09/2026)
        carro.setModelo(carroAtualizado.getModelo());
        carro.setMotor(carroAtualizado.getMotor());
        carro.setAno(carroAtualizado.getAno());
        Carro carroSalvo = carroRepository.save(carro);
        return new CarroDTO(carroSalvo);
    }

    public void deletar(Long id) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Carro não encontrado pelo ID: " + id));   // criado (23/09/2026)
        carroRepository.delete(carro);
    }
}