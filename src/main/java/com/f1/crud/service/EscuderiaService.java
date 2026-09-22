package com.f1.crud.service;

import com.f1.crud.domain.Escuderia;
import com.f1.crud.exception.NotFoundException; //criado hoje
import com.f1.crud.repository.EscuderiaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EscuderiaService {

    private final EscuderiaRepository repository;

    public EscuderiaService(EscuderiaRepository repository) {
        this.repository = repository;
    }

    public List<Escuderia> listarTodas() {
        return repository.findAll();
    }

    public Escuderia buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Escuderia não encontrada para o ID: " + id));
    }

    public Escuderia salvar(Escuderia escuderia) {
        return repository.save(escuderia);
    }

    public Escuderia atualizar(Long id, Escuderia escuderiaAtualizada) {
        Escuderia escuderia = buscarPorId(id);
        escuderia.setNome(escuderiaAtualizada.getNome());
        escuderia.setPaisOrigem(escuderiaAtualizada.getPaisOrigem());
        return repository.save(escuderia);
    }

    public void deletar(Long id) {
        Escuderia escuderia = buscarPorId(id);
        repository.delete(escuderia);
    }
}