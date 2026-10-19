package com.f1.crud.controller;

import com.f1.crud.domain.Atividade;
import com.f1.crud.repository.AtividadeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atividades")
public class AtividadeController {

    private final AtividadeRepository atividadeRepository;

    public AtividadeController(AtividadeRepository atividadeRepository) {
        this.atividadeRepository = atividadeRepository;
    }

    @GetMapping
    public List<Atividade> listarAtividades() {
        return atividadeRepository.findTop10ByOrderByTimestampDesc();
    }
}