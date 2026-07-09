package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@RequestMapping("/api/reportes") 


public class ReporteController {

    private final ReporteService service;

    public ReporteController(ReporteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody ReporteDTO request) {
        try {
            ReporteIncendio guardado = service.guardarNuevoReporte(request);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Si el validador falla, devolvemos un JSON con el mensaje de error
            Map<String, String> respuestaError = new HashMap<>();
            respuestaError.put("error", e.getMessage());
            return new ResponseEntity<>(respuestaError, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<ReporteIncendio>> listar(@org.springframework.web.bind.annotation.RequestParam(required = false) String correo) {

        if (correo != null && !correo.isEmpty()) {
            return ResponseEntity.ok(service.listarPorCorreo(correo));
        }

        return ResponseEntity.ok(service.listarTodos());
    }
}
