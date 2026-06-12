package com.example.demo.factory;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.example.demo.model.ReporteDTO;
import com.example.demo.model.ReporteIncendio;

@Component
public class ReporteFactory {

    public static ReporteIncendio crearReporte(ReporteDTO dto) {
        ReporteIncendio reporte = new ReporteIncendio();
        reporte.setLatitud(dto.getLatitud());
        reporte.setLongitud(dto.getLongitud());
        reporte.setTipoIncendio(dto.getTipoIncendio());
        reporte.setFechaReporte(LocalDateTime.now());
        return reporte;
    }
}
