// Criado   (23/09/2026) ; // Atualizado (02/10/2026)
package com.f1.crud.controller;         

import com.f1.crud.dto.EscuderiaRequestDTO;
import com.f1.crud.dto.EscuderiaResponseDTO;
import com.f1.crud.service.EscuderiaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/escuderias")
public class EscuderiaController {

    // Atualizado (02/10/2026)
    private final EscuderiaService escuderiaService;

    // Atualizado (02/10/2026)
    public EscuderiaController(EscuderiaService escuderiaService) {
        this.escuderiaService = escuderiaService;
    }

    // Criado (02/10/2026)
    @PostMapping
    public ResponseEntity<EscuderiaResponseDTO> criar(
        @Valid
        @RequestBody EscuderiaRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(escuderiaService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<EscuderiaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(escuderiaService.listarTodos());
    }

    // Criado (02/10/2026)
    @GetMapping("/paginado")
    public ResponseEntity<Page<EscuderiaResponseDTO>> listarPaginado(Pageable pageable) {
        return ResponseEntity.ok(escuderiaService.listarPaginado(pageable));
    }

    // Atualizado (24/09/2026)
    @GetMapping("/{id}")
    public ResponseEntity<EscuderiaResponseDTO> buscarPorId(
        @PathVariable Long id) {
        return ResponseEntity.ok(escuderiaService.buscarPorId(id));
    }

    // Criado (24/09/2026); Atualizado (02/10/2026)
    @PutMapping("/{id}")
    public ResponseEntity<EscuderiaResponseDTO> atualizar(
        @PathVariable Long id, 
        @Valid 
        @RequestBody EscuderiaRequestDTO dto) {
        return ResponseEntity.ok(escuderiaService.atualizar(id, dto));
    }

    // Atualizado (02/10/2026)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
        @PathVariable Long id) {
        escuderiaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}