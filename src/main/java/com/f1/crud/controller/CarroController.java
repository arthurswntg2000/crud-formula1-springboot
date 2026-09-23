package com.f1.crud.controller;

import com.f1.crud.domain.Carro;
import com.f1.crud.dto.CarroDTO;
import com.f1.crud.service.CarroService;
import org.springframework.data.domain.Page;    // criado (23/09/2026)
import org.springframework.data.domain.Pageable;    // criado (23/09/2026)
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/carros")
public class CarroController {

    private final CarroService service;

    public CarroController(CarroService service) {
        this.service = service;
    }

    // Atualizado em (23/09/2026)
    @GetMapping
    public ResponseEntity<Page<CarroDTO>> listarTodos(Pageable pageable) {
        return ResponseEntity.ok(service.listarTodos(pageable));
    }

    // Atualizado em (23/09/2026)
    @GetMapping("/{id}")
    public ResponseEntity<CarroDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // criado (22/09/2026); // Atualizado em (23/09/2026)
    @GetMapping("/buscar-por-modelo")
    public ResponseEntity<Page<CarroDTO>> buscarPorModelo(@RequestParam String modelo, Pageable pageable) {
        return ResponseEntity.ok(service.buscarPorModelo(modelo, pageable));
    }

    // criado (22/09/2026); // Atualizado em (23/09/2026)
    @GetMapping("/buscar-por-escuderia")
    public ResponseEntity<Page<CarroDTO>> buscarPorNomeEscuderia(@RequestParam String nome, Pageable pageable) {
        return ResponseEntity.ok(service.buscarPorNomeEscuderia(nome, pageable));
    }

    // criado (22/09/2026); // Atualizado em (23/09/2026)
    @GetMapping("/buscar-por-ano")
    public ResponseEntity<Page<CarroDTO>> buscarPorAno(@RequestParam Integer ano, Pageable pageable) {
        return ResponseEntity.ok(service.buscarPorAno(ano, pageable));
    }

    // criado (22/09/2026); // Atualizado em (23/09/2026)
    @GetMapping("/buscar-por-motor")
    public ResponseEntity<Page<CarroDTO>> buscarPorMotor(@RequestParam String motor, Pageable pageable) {
        return ResponseEntity.ok(service.buscarPorMotor(motor, pageable));
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

