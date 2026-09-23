package com.f1.crud.controller;     // Criado (23/09/2026)

import com.f1.crud.domain.Piloto;
import com.f1.crud.dto.PilotoDTO;
import com.f1.crud.service.PilotoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pilotos")
public class PilotoController {

    private final PilotoService service;

    public PilotoController(PilotoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<PilotoDTO>> listarTodos(Pageable pageable) {
        return ResponseEntity.ok(service.listarTodos(pageable));
    }

    @PostMapping
    public ResponseEntity<PilotoDTO> salvar(@RequestBody Piloto piloto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(piloto));
    }

    @PutMapping("/{pilotoId}/escuderias/{escuderiaId}")
    public ResponseEntity<PilotoDTO> associarEscuderia(@PathVariable Long pilotoId, @PathVariable Long escuderiaId) {
        return ResponseEntity.ok(service.associarEscuderia(pilotoId, escuderiaId));
    }
}