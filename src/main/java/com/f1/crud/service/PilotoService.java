package com.f1.crud.service;    // Criado (23/09/2026)

import com.f1.crud.repository.PilotoRepository;
import com.f1.crud.domain.Escuderia;
import com.f1.crud.domain.Piloto;
import com.f1.crud.dto.PilotoRequestDTO;
import org.hibernate.Hibernate;     // Atualizado (24/09/2026)
import com.f1.crud.exception.NotFoundException;
import com.f1.crud.repository.EscuderiaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

// Mudar PilotoService para PilotoServiceImpl
// Criar a interface do Service
public class PilotoService {

    private final PilotoRepository pilotoRepository;
    private final EscuderiaRepository escuderiaRepository;

    public PilotoService(PilotoRepository pilotoRepository, EscuderiaRepository escuderiaRepository) {
        this.pilotoRepository = pilotoRepository;
        this.escuderiaRepository = escuderiaRepository;
    }

    // Criado (24/09/2026)
    @Transactional(readOnly = true)
    public Page<PilotoRequestDTO> listarTodos(Pageable pageable) {
        return pilotoRepository.findAll(pageable).map(PilotoRequestDTO::new);
    }

    public PilotoRequestDTO salvar(Piloto piloto) {
        Piloto pilotoSalvo = pilotoRepository.save(piloto);
        return new PilotoRequestDTO(pilotoSalvo);
    }

    // Vincula um piloto a uma escuderia
    public PilotoRequestDTO associarEscuderia(Long pilotoId, Long escuderiaId) {
        Piloto piloto = pilotoRepository.findById(pilotoId)
                .orElseThrow(() -> new NotFoundException("Piloto não encontrado pelo ID: " + pilotoId));
        
        Escuderia escuderia = escuderiaRepository.findById(escuderiaId)
                .orElseThrow(() -> new NotFoundException("Escuderia não encontrada pelo ID: " + escuderiaId));

        piloto.getEscuderias().add(escuderia);
        Piloto pilotoSalvo = pilotoRepository.save(piloto);     // Atualizado (24/09/2026)

        // Força a carregar os dados proxy da coleção de escuderias
        Hibernate.initialize(pilotoSalvo.getEscuderias());

        return new PilotoRequestDTO(pilotoSalvo);
    }
}