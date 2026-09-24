package com.f1.crud.controller;

import com.f1.crud.domain.Escuderia;
import com.f1.crud.dto.EscuderiaRequestDTO;
import com.f1.crud.service.EscuderiaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/escuderias")
public class EscuderiaController {

    private final EscuderiaService service;

    public EscuderiaController(EscuderiaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EscuderiaRequestDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Escuderia> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Escuderia> criar(@RequestBody Escuderia escuderia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(escuderia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Escuderia> atualizar(@PathVariable Long id, @RequestBody Escuderia escuderia) {
        return ResponseEntity.ok(service.atualizar(id, escuderia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}