package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.ReporteDTO;
import com.example.demo.model.ReporteIncendio;
import com.example.demo.model.Usuario;
import com.example.demo.repository.ReporteRepository;
import com.example.demo.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class ReporteServiceTest {

    @InjectMocks
    private ReporteService reporteService;

    @Mock
    private ReporteRepository reporteRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    public void testListarTodos() {
        ReporteIncendio r1 = new ReporteIncendio();
        ReporteIncendio r2 = new ReporteIncendio();

        when(reporteRepository.findAll()).thenReturn(Arrays.asList(r1, r2));

        List<ReporteIncendio> reportes = reporteService.listarTodos();

        assertNotNull(reportes);
        assertEquals(2, reportes.size());
        verify(reporteRepository, times(1)).findAll();
    }

    @Test
    public void testListarPorCorreo() {
        ReporteIncendio r1 = new ReporteIncendio();
        r1.setTipoIncendio("Forestal");
        
        when(reporteRepository.findByUsuarioCorreo("ciudadano@gmail.com")).thenReturn(Arrays.asList(r1));

        List<ReporteIncendio> reportes = reporteService.listarPorCorreo("ciudadano@gmail.com");

        assertNotNull(reportes);
        assertEquals(1, reportes.size());
        assertEquals("Forestal", reportes.get(0).getTipoIncendio());
        verify(reporteRepository, times(1)).findByUsuarioCorreo("ciudadano@gmail.com");
    }

    @Test
    public void testGuardarNuevoReporteExitoso() {
        ReporteDTO dto = new ReporteDTO();
        dto.setTipoIncendio("Forestal");
        dto.setLatitud(-33.0);
        dto.setLongitud(-70.0);
        dto.setCorreoUsuario("maria@gmail.com");

        Usuario usuarioFalso = new Usuario();
        usuarioFalso.setCorreo("maria@gmail.com");

        when(usuarioRepository.findByCorreo("maria@gmail.com")).thenReturn(Optional.of(usuarioFalso));
        when(reporteRepository.save(any(ReporteIncendio.class))).thenAnswer(i -> i.getArguments()[0]);

        ReporteIncendio reporteGuardado = reporteService.guardarNuevoReporte(dto);

        assertNotNull(reporteGuardado);
        assertEquals(usuarioFalso, reporteGuardado.getUsuario()); 
        assertEquals("Forestal", reporteGuardado.getTipoIncendio());
        
        verify(usuarioRepository, times(1)).findByCorreo("maria@gmail.com");
        verify(reporteRepository, times(1)).save(any(ReporteIncendio.class));
    }
}