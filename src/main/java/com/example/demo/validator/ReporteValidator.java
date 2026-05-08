package com.example.demo.validator;


import org.springframework.stereotype.Component;

import com.example.demo.model.ReporteDTO;

@Component
public class ReporteValidator {

    public void validar(ReporteDTO dto) {
        // 1. Validar Tipo de Incendio
        if (dto.getTipoIncendio() == null || dto.getTipoIncendio().trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de incendio es obligatorio.");
        }

        // 2. Validar Latitud (Debe estar entre -90 y 90 grados)
        if (dto.getLatitud() == null) {
            throw new IllegalArgumentException("La latitud es obligatoria.");
        }
        if (dto.getLatitud() < -90.0 || dto.getLatitud() > 90.0) {
            throw new IllegalArgumentException("La latitud debe estar entre -90 y 90.");
        }

        // 3. Validar Longitud (Debe estar entre -180 y 180 grados)
        if (dto.getLongitud() == null) {
            throw new IllegalArgumentException("La longitud es obligatoria.");
        }
        if (dto.getLongitud() < -180.0 || dto.getLongitud() > 180.0) {
            throw new IllegalArgumentException("La longitud debe estar entre -180 y 180.");
        }
    }
}
