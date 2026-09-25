package com.f1.crud.controller;         // Criado   (23/09/2026)

import com.f1.crud.dto.EscuderiaRequestDTO;
import com.f1.crud.dto.EscuderiaResponseDTO;
import com.f1.crud.service.EscuderiaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/escuderias")
public class EscuderiaController {

    private final EscuderiaService service;

    public EscuderiaController(EscuderiaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<EscuderiaResponseDTO>> listarTodas(Pageable pageable) {
        return ResponseEntity.ok(service.listarPaginado(pageable));
    }

    // Atualizado (24/09/2026)
    @GetMapping("/{id}")
    public ResponseEntity<EscuderiaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // Atualizado (24/09/2026)
    @PostMapping
    public ResponseEntity<EscuderiaResponseDTO> criar(@RequestBody EscuderiaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    // Atualizado (24/09/2026)
    @PutMapping("/{id}")
    public ResponseEntity<EscuderiaResponseDTO> atualizar(@PathVariable Long id, @RequestBody EscuderiaRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}