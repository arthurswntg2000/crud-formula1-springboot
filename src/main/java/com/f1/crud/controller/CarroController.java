package com.f1.crud.controller;

import com.f1.crud.domain.Carro;
import com.f1.crud.DTO.CarroDTO;    // criado (23/09/2026)
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

    // Atualizado em (23/09/2026)
    @GetMapping
    public ResponseEntity<List<CarroDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // Atualizado em (23/09/2026)
    @GetMapping("/{id}")
    public ResponseEntity<CarroDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // criado (22/09/2026); // Atualizado em (23/09/2026)
    @GetMapping("/buscar-por-modelo")
    public ResponseEntity<List<CarroDTO>> buscarPorModelo(@RequestParam String modelo) {
        return ResponseEntity.ok(service.buscarPorModelo(modelo));
    }

    // criado (22/09/2026); // Atualizado em (23/09/2026)
    @GetMapping("/buscar-por-escuderia")
    public ResponseEntity<List<CarroDTO>> buscarPorNomeEscuderia(@RequestParam String nome) {
        return ResponseEntity.ok(service.buscarPorNomeEscuderia(nome));
    }

    // criado (22/09/2026); // Atualizado em (23/09/2026)
    @GetMapping("/buscar-por-ano")
    public ResponseEntity<List<CarroDTO>> buscarPorAno(@RequestParam Integer ano) {
        return ResponseEntity.ok(service.buscarPorAno(ano));
    }

    // criado (22/09/2026); // Atualizado em (23/09/2026)
    @GetMapping("/buscar-por-motor")
    public ResponseEntity<List<CarroDTO>> buscarPorMotor(@RequestParam String motor) {
        return ResponseEntity.ok(service.buscarPorMotor(motor));
    }

    // Atualizado em (23/09/2026)
    @PostMapping("/escuderia/{escuderiaId}") 
    public ResponseEntity<CarroDTO> salvar(@RequestBody Carro carro, @PathVariable Long escuderiaId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(carro, escuderiaId));
    }

    // Atualizado em (23/09/2026)
    @PutMapping("/{id}")
    public ResponseEntity<CarroDTO> atualizar(@PathVariable Long id, @RequestBody Carro carro) {
        return ResponseEntity.ok(service.atualizar(id, carro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}