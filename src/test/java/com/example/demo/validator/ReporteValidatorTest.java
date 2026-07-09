package com.example.demo.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.example.demo.model.ReporteDTO;

public class ReporteValidatorTest {

    // Método auxiliar para crear un DTO válido
    private ReporteDTO crearReporteValido() {
        ReporteDTO dto = new ReporteDTO();
        dto.setTipoIncendio("Forestal");
        dto.setLatitud(-33.4569);
        dto.setLongitud(-70.6482);
        dto.setCorreoUsuario("ciudadano@gmail.com");
        return dto;
    }

    @Test
    public void testReporteValidoPasaValidacion() {
        ReporteDTO dto = crearReporteValido();

        assertDoesNotThrow(() -> {
            ReporteValidator.validar(dto);
        });
    }

    @Test
    public void testReporteNuloLanzaExcepcion() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ReporteValidator.validar(null);
        });
        assertTrue(exception.getMessage().contains("El reporte no puede ser nulo"));
    }

    @Test
    public void testTipoIncendioVacioLanzaExcepcion() {
        ReporteDTO dto = crearReporteValido();
        dto.setTipoIncendio(""); // Tipo vacío

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ReporteValidator.validar(dto);
        });
        assertTrue(exception.getMessage().contains("El tipo de incendio es obligatorio"));
    }

    @Test
    public void testLatitudFueraDeRangoLanzaExcepcion() {
        ReporteDTO dto = crearReporteValido();
        dto.setLatitud(100.0); // Rango inválido (debe ser entre -90 y 90)

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ReporteValidator.validar(dto);
        });
        assertTrue(exception.getMessage().contains("entre -90 y 90"));
    }

    @Test
    public void testLongitudFueraDeRangoLanzaExcepcion() {
        ReporteDTO dto = crearReporteValido();
        dto.setLongitud(-200.0); // Rango inválido (debe ser entre -180 y 180)

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ReporteValidator.validar(dto);
        });
        assertTrue(exception.getMessage().contains("entre -180 y 180"));
    }

    @Test
    public void testCorreoVacioLanzaExcepcion() {
        ReporteDTO dto = crearReporteValido();
        dto.setCorreoUsuario(null); // Correo nulo

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ReporteValidator.validar(dto);
        });
        assertTrue(exception.getMessage().contains("El correo del usuario es obligatorio"));
    }
}