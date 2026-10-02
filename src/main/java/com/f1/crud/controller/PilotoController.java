// Criado (23/09/2026); Atualizado (02/10/2026)
package com.f1.crud.controller;     

import com.f1.crud.dto.PilotoRequestDTO;    // criado (24/09/2026)
import com.f1.crud.dto.PilotoResponseDTO;   // criado (24/09/2026)
import com.f1.crud.service.PilotoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pilotos")
public class PilotoController {

    private final PilotoService pilotoService;  // Atualizado (02/10/2026)

    // Atualizado (02/10/2026)
    public PilotoController(PilotoService pilotoService) {
        this.pilotoService = pilotoService;
    }

    // Atualizado (02/10/2026)
    @PostMapping
    public ResponseEntity<PilotoResponseDTO> criar(
        @Valid 
        @RequestBody PilotoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pilotoService.criar(dto));
    }

    // Criado (24/09/2026); // Atualizado (02/10/2026)
    @GetMapping
    public ResponseEntity<List<PilotoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(pilotoService.listarTodos());
    }

    // Criado (02/10/2026)
    @GetMapping("/paginado")
    public ResponseEntity<Page<PilotoResponseDTO>> listarPaginado(Pageable pageable) {
        return ResponseEntity.ok(pilotoService.listarPaginado(pageable));
    }

    // Criado (24/09/2026); Atualizado (02/10/2026)
    @GetMapping("/{id}")
    public ResponseEntity<PilotoResponseDTO> buscarPorId(
        @PathVariable Long id) {
        return ResponseEntity.ok(pilotoService.buscarPorId(id));
    }

    // Criado (02/10/2026)
    @GetMapping("/buscar/nome")
    public ResponseEntity<Page<PilotoResponseDTO>> buscarPorNome(
        @RequestParam String nome, 
        Pageable pageable) {
        return ResponseEntity.ok(pilotoService.buscarPorNome(nome, pageable));
    }

    // Criado (24/09/2026); Atualizado (02/10/2026)
    @PutMapping("/{id}")
    public ResponseEntity<PilotoResponseDTO> atualizar(
        @PathVariable Long id, 
        @Valid
        @RequestBody PilotoRequestDTO dto) {
        return ResponseEntity.ok(pilotoService.atualizar(id, dto));
    }

    @PutMapping("/{pilotoId}/escuderias/{escuderiaId}")
    public ResponseEntity<PilotoResponseDTO> adicionarEscuderia(
        @PathVariable Long pilotoId, 
        @PathVariable Long escuderiaId) {
        return ResponseEntity.ok(pilotoService.adicionarEscuderia(pilotoId, escuderiaId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
        @PathVariable Long id) {
        pilotoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}