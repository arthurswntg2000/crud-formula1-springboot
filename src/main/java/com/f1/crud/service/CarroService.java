package com.f1.crud.service;

import com.f1.crud.domain.Carro;
import com.f1.crud.domain.Escuderia;
import com.f1.crud.DTO.CarroDTO;    // criado (23/09/2026)
import com.f1.crud.exception.NotFoundException;  // Criado (22/09/2026)
import com.f1.crud.repository.CarroRepository;
import com.f1.crud.repository.EscuderiaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    private final CarroRepository carroRepository;
    private final EscuderiaRepository escuderiaRepository;

    public CarroService(CarroRepository carroRepository, EscuderiaRepository escuderiaRepository) {
        this.carroRepository = carroRepository;
        this.escuderiaRepository = escuderiaRepository;
    }

    public List<CarroDTO> listarTodos() {
        return carroRepository.findAll().stream().map(CarroDTO::new).toList();  //Atualizado dia (23/09/2026)
    }

    public CarroDTO buscarPorId(Long id) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Carro não encontrado pelo ID: " + id));  // Criado (22/09/2026)
        return new CarroDTO(carro);
    }

    // criado (22/09/2026);     Atualizado (23/09/2026)
    public List<CarroDTO> buscarPorModelo(String modelo) {
        List<Carro> carros = carroRepository.buscarPorModelo(modelo);
        if (carros.isEmpty()) {
            throw new NotFoundException("Nenhum carro encontrado pelo modelo informado: " + modelo);
        }
        return carros.stream().map(CarroDTO::new).toList();
    }

    // criado (22/09/2026);     Atualizado (23/09/2026)
    public List<CarroDTO> buscarPorNomeEscuderia(String nomeEscuderia) {
        List<Carro> carros = carroRepository.buscarPorNomeEscuderia(nomeEscuderia);
        if (carros.isEmpty()) {
            throw new NotFoundException("Nenhum carro encontrado para a escuderia informada: " + nomeEscuderia);
        }
        return carros.stream().map(CarroDTO::new).toList();
    }

    // criado (22/09/2026);     Atualizado (23/09/2026)
    public List<CarroDTO> buscarPorAno(Integer ano) {
        List<Carro> carros = carroRepository.buscarPorAno(ano);
        if (carros.isEmpty()) {
            throw new NotFoundException("Nenhum carro encontrado pelo ano de " + ano);
        }
        return carros.stream().map(CarroDTO::new).toList();
    }

    // criado (22/09/2026);     Atualizado (23/09/2026)
    public List<CarroDTO> buscarPorMotor(String motor) {
        List<Carro> carros = carroRepository.buscarPorMotor(motor);
        if (carros.isEmpty()) {
            throw new NotFoundException("Nenhum carro encontrado pelo motor informado: " + motor);
        }
        return carros.stream().map(CarroDTO::new).toList();
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