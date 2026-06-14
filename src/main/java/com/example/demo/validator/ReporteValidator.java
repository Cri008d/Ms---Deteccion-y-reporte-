package com.example.demo.validator;

import org.springframework.util.Assert;
import com.example.demo.model.ReporteDTO;

public class ReporteValidator {

    public static void validar(ReporteDTO dto) {
        Assert.notNull(dto, "El reporte no puede ser nulo");

        // Validar Tipo de Incendio
        Assert.hasText(dto.getTipoIncendio(), "El tipo de incendio es obligatorio.");

        // Validar Latitud (
        Assert.notNull(dto.getLatitud(), "La latitud es obligatoria.");
        Assert.isTrue(dto.getLatitud() >= -90.0 && dto.getLatitud() <= 90.0, 
            "La latitud debe estar entre -90 y 90.");

        // Validar Longitud 
        Assert.notNull(dto.getLongitud(), "La longitud es obligatoria.");
        Assert.isTrue(dto.getLongitud() >= -180.0 && dto.getLongitud() <= 180.0, 
            "La longitud debe estar entre -180 y 180.");

        // Validar Correo (Para evitar que intenten buscar un usuario nulo en la BD)
        Assert.hasText(dto.getCorreoUsuario(), "El correo del usuario es obligatorio para crear un reporte.");
    }
}
