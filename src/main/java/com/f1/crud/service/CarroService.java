package com.f1.crud.service;

import com.f1.crud.domain.Carro;
import com.f1.crud.domain.Escuderia;
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

    public List<Carro> listarTodos() {
        return carroRepository.findAll();
    }

    public Carro buscarPorId(Long id) {
        return carroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado com o ID: " + id));
    }

    public Carro salvar(Carro carro, Long escuderiaId) {
        Escuderia escuderia = escuderiaRepository.findById(escuderiaId)
                .orElseThrow(() -> new RuntimeException("Escuderia não encontrada com o ID: " + escuderiaId));
        carro.setEscuderia(escuderia);
        return carroRepository.save(carro);
    }

    public Carro atualizar(Long id, Carro carroAtualizado) {
        Carro carro = buscarPorId(id);
        carro.setModelo(carroAtualizado.getModelo());
        carro.setMotor(carroAtualizado.getMotor());
        carro.setAno(carroAtualizado.getAno());
        return carroRepository.save(carro);
    }

    public void deletar(Long id) {
        Carro carro = buscarPorId(id);
        carroRepository.delete(carro);
    }
}