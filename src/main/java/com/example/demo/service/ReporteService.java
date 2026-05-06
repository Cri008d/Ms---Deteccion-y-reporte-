package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.factory.ReporteFactory;
import com.example.demo.model.ReporteDTO;
import com.example.demo.model.ReporteIncendio;
import com.example.demo.repository.ReporteRepository;
import com.example.demo.validator.ReporteValidator;

@Service
public class ReporteService {

    private final ReporteRepository repository;
    private final ReporteFactory factory;
    private final ReporteValidator validator; // Inyectamos el validador

    public ReporteService(ReporteRepository repository, ReporteFactory factory, ReporteValidator validator) {
        this.repository = repository;
        this.factory = factory;
        this.validator = validator;
    }

    public ReporteIncendio guardarNuevoReporte(ReporteDTO dto) {
        //  Validamos los datos primero
        validator.validar(dto);

        //  Si pasa la validación, armamos el objeto y lo guardamos
        ReporteIncendio reporteArmado = factory.crearDesdeDTO(dto);
        return repository.save(reporteArmado);
    }

    public List<ReporteIncendio> listarTodos() {
        return repository.findAll();
    }
}

