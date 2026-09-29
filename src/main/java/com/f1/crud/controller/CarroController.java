package com.f1.crud.controller;

import com.f1.crud.dto.CarroRequestDTO;
import com.f1.crud.dto.CarroResponseDTO;    // criado (24/09/2026)
import com.f1.crud.service.CarroService;
import org.springframework.data.domain.Page;    // criado (23/09/2026)
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;    // criado (23/09/2026)
import org.springframework.data.domain.Sort;
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

    // Método auxiliar para traduzir 'nomeEscuderia' para 'escuderia.nome'
    private Pageable ajustarOrdenacao(Pageable pageable) {
        if (pageable.getSort().isUnsorted()) {
            return pageable;
        }

        Sort sortAjustado = Sort.by(
            pageable.getSort().stream().map(order -> {
                if ("nomeEscuderia".equalsIgnoreCase(order.getProperty())) {
                    return new Sort.Order(order.getDirection(), "escuderia.nome");
                }
                return order;
            }).toList()
        );

        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sortAjustado);
    }

    // Atualizado em (24/09/2026)
    @GetMapping
    public ResponseEntity<Page<CarroResponseDTO>> listarTodos(Pageable pageable) {
        return ResponseEntity.ok(service.listarPaginado(ajustarOrdenacao(pageable)));
    }

    // Atualizado em (24/09/2026)
    @GetMapping("/{id}")
    public ResponseEntity<CarroResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // criado (22/09/2026); // Atualizado em (24/09/2026)
    @GetMapping("/buscar-por-modelo")
    public ResponseEntity<Page<CarroResponseDTO>> buscarPorModelo(@RequestParam String modelo, Pageable pageable) {
        return ResponseEntity.ok(service.buscarPorModelo(modelo, ajustarOrdenacao(pageable)));
    }

    // criado (22/09/2026); // Atualizado em (24/09/2026)
    @GetMapping("/buscar-por-escuderia")
    public ResponseEntity<Page<CarroResponseDTO>> buscarPorNomeEscuderia(@RequestParam String nome, Pageable pageable) {
        return ResponseEntity.ok(service.buscarPorEscuderia(nome, ajustarOrdenacao(pageable)));
    }

    // criado (22/09/2026); // Atualizado em (24/09/2026)
    @GetMapping("/buscar-por-ano")
    public ResponseEntity<Page<CarroResponseDTO>> buscarPorAno(@RequestParam Integer ano, Pageable pageable) {
        return ResponseEntity.ok(service.buscarPorAno(ano, ajustarOrdenacao(pageable)));
    }

    // criado (22/09/2026); // Atualizado em (24/09/2026)
    @GetMapping("/buscar-por-motor")
    public ResponseEntity<Page<CarroResponseDTO>> buscarPorMotor(@RequestParam String motor, Pageable pageable) {
        return ResponseEntity.ok(service.buscarPorMotor(motor, ajustarOrdenacao(pageable)));
    }

    // Criado em (24/09/2026)
    @PostMapping
    public ResponseEntity<CarroResponseDTO> salvar(@RequestBody CarroRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    // Atualizado em (23/09/2026)
    @PutMapping("/{id}")
    public ResponseEntity<CarroResponseDTO> atualizar(@PathVariable Long id, @RequestBody CarroRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}