package com.example.demo.factory;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.example.demo.model.ReporteDTO;
import com.example.demo.model.ReporteIncendio;

@Component
public class ReporteFactory {

    public ReporteIncendio crearDesdeDTO(ReporteDTO dto) {
        ReporteIncendio reporte = new ReporteIncendio();
        reporte.setLatitud(dto.getLatitud());
        reporte.setLongitud(dto.getLongitud());
        reporte.setTipoIncendio(dto.getTipoIncendio());
        
        // Datos que el sistema asigna automáticamente
        reporte.setEstado("PENDIENTE");
        reporte.setFechaReporte(Instant.now());
        
        return reporte;
    }
}
