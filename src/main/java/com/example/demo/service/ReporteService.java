package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.factory.ReporteFactory;
import com.example.demo.model.ReporteDTO;
import com.example.demo.model.ReporteIncendio;
import com.example.demo.repository.ReporteRepository;

@Service
public class ReporteService {

    private final ReporteRepository repository;
    private final ReporteFactory factory;

    public ReporteService(ReporteRepository repository, ReporteFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    public ReporteIncendio guardarNuevoReporte(ReporteDTO dto) {
        // Usa la fábrica para crear y el repositorio para guardar
        ReporteIncendio reporteArmado = factory.crearDesdeDTO(dto);
        return repository.save(reporteArmado);
    }

    public List<ReporteIncendio> listarTodos() {
        return repository.findAll();
    }
}
