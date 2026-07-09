package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.factory.ReporteFactory;
import com.example.demo.model.ReporteDTO;
import com.example.demo.model.ReporteIncendio;
import com.example.demo.model.Usuario;
import com.example.demo.repository.ReporteRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.validator.ReporteValidator;

@Service
public class ReporteService {

    private ReporteRepository reporteRepository; 
    private UsuarioRepository usuarioRepository;

    public ReporteService(ReporteRepository reporteRepository, UsuarioRepository usuarioRepository) {
        this.reporteRepository = reporteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public ReporteIncendio guardarNuevoReporte(ReporteDTO dto) {
        ReporteValidator.validar(dto);

        Usuario usuario = usuarioRepository.findByCorreo(dto.getCorreoUsuario())
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        ReporteIncendio nuevoReporte = ReporteFactory.crearReporte(dto);
        
        nuevoReporte.setUsuario(usuario);

        return reporteRepository.save(nuevoReporte);
    }

    public List<ReporteIncendio> listarTodos() {
        return reporteRepository.findAll();
    }

    public List<ReporteIncendio> listarPorCorreo(String correo) {
    return reporteRepository.findByUsuarioCorreo(correo);
}


}

