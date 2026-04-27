package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ReporteDTO;
import com.example.demo.model.ReporteIncendio;
import com.example.demo.service.ReporteService;

@RestController
@RequestMapping("/api/reportes") // Ruta personalizada para tu API DR
public class ReporteController {

    private final ReporteService service;

    public ReporteController(ReporteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ReporteIncendio> crear(@RequestBody ReporteDTO request) {
        ReporteIncendio guardado = service.guardarNuevoReporte(request);
        return new ResponseEntity<>(guardado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReporteIncendio>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }
}
