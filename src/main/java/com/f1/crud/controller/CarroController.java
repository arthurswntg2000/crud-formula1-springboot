// Criado (24/09/2026); Atualizado (02/10/2026)
package com.f1.crud.controller;

import com.f1.crud.dto.CarroRequestDTO;
import com.f1.crud.dto.CarroResponseDTO;    // criado (24/09/2026)
import com.f1.crud.service.CarroService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;    // criado (23/09/2026)
import org.springframework.data.domain.Pageable;    // criado (23/09/2026)
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carros")
public class CarroController {

    // Atualizado (02/10/2026)
    private final CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    // Criado (02/10/2026)
    @PostMapping
    public ResponseEntity<CarroResponseDTO> criar(
        @Valid 
        @RequestBody CarroRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carroService.criar(dto));
    }

    // Atualizado (02/10/2026)
    @GetMapping
    public ResponseEntity<List<CarroResponseDTO>> listarTodos() {
        return ResponseEntity.ok(carroService.listarTodos());
    }

    // Criado (02/10/2026)
    @GetMapping("/paginado")
    public ResponseEntity<Page<CarroResponseDTO>> listarPaginado(Pageable pageable) {
        return ResponseEntity.ok(carroService.listarPaginado(pageable));
    }

    // Criado (02/10/2026)
    @GetMapping("/{id}")
    public ResponseEntity<CarroResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(carroService.buscarPorId(id));
    }

    // Atualizado em (23/09/2026)
    @PutMapping("/{id}")
    public ResponseEntity<CarroResponseDTO> atualizar(
        @PathVariable Long id, 
        @RequestBody CarroRequestDTO dto) {
        return ResponseEntity.ok(carroService.atualizar(id, dto));
    }

    // Atualizado (02/10/2026)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        carroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}