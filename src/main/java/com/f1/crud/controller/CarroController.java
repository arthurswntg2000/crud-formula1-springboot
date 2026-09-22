package com.f1.crud.controller;

import com.f1.crud.domain.Carro;
import com.f1.crud.service.CarroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carros")
public class CarroController {

    private final CarroService service;

    public CarroController(CarroService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Carro>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // criado (22/09/2026)
    @GetMapping("/buscar-por-modelo")
    public ResponseEntity<List<Carro>> buscarPorModelo(@RequestParam String modelo) {
        return ResponseEntity.ok(service.buscarPorModelo(modelo));
    }

    // criado (22/09/2026)
    @GetMapping("/buscar-por-escuderia")
    public ResponseEntity<List<Carro>> buscarPorEscuderia(@RequestParam String nome) {
        return ResponseEntity.ok(service.buscarPorNomeEscuderia(nome));
    }

    @PostMapping("/escuderia/{escuderiaId}")
    public ResponseEntity<Carro> criar(@RequestBody Carro carro, @PathVariable Long escuderiaId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(carro, escuderiaId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carro> atualizar(@PathVariable Long id, @RequestBody Carro carro) {
        return ResponseEntity.ok(service.atualizar(id, carro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}