package com.f1.crud.controller;     // Criado (23/09/2026)

import com.f1.crud.dto.PilotoRequestDTO;    // criado (24/09/2026)
import com.f1.crud.dto.PilotoResponseDTO;   // criado (24/09/2026)
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

    // Atualizado (24/09/2026)
    @GetMapping
    public ResponseEntity<Page<PilotoResponseDTO>> listarTodos(Pageable pageable) {
        return ResponseEntity.ok(service.listarPaginado(pageable));
    }

    // Atualizado (24/09/2026)
    @GetMapping("/{id}")
    public ResponseEntity<PilotoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // Atualizado (24/09/2026)
    @PostMapping
    public ResponseEntity<PilotoResponseDTO> salvar(@RequestBody PilotoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    // Atualizado (24/09/2026)
    @PutMapping("/{id}")
    public ResponseEntity<PilotoResponseDTO> atualizar(@PathVariable Long id, @RequestBody PilotoRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}