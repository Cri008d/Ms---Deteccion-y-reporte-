package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
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

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

// Usamos Mockito puro para no levantar el contexto de Spring (evita errores de Base de Datos)
@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    // Método auxiliar para crear un usuario de prueba
    private Usuario createUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Maria Lopez");
        usuario.setCorreo("maria@gmail.com");
        usuario.setContraseña("PasswordFuerte123@");
        return usuario;
    }

    @Test
    public void testObtenerTodos() {
        Usuario usuario1 = createUsuario();
        Usuario usuario2 = new Usuario();
        usuario2.setNombre("Juan Perez");
        
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario1, usuario2));

        List<Usuario> usuarios = usuarioService.obtenerTodos();

        assertNotNull(usuarios);
        assertEquals(2, usuarios.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    public void testObtenerPorId() {
        Usuario usuario = createUsuario();
        
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        Usuario usuarioEncontrado = usuarioService.obtenerPorId(1);

        assertNotNull(usuarioEncontrado);
        assertEquals("Maria Lopez", usuarioEncontrado.getNombre());
        verify(usuarioRepository, times(1)).findById(1);
    }

    @Test
    public void testGuardarUsuario() {
        Usuario usuario = createUsuario();

        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(i -> i.getArguments()[0]);

        Usuario usuarioGuardado = usuarioService.guardar(usuario);

        assertNotNull(usuarioGuardado);
        // Validamos que se haya encriptado
        assertNotEquals("PasswordFuerte123@", usuarioGuardado.getContraseña());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    public void testActualizarUsuario() {
        Usuario usuarioExistente = createUsuario();
        
        Usuario nuevosDatos = new Usuario();
        nuevosDatos.setNombre("Maria Actualizada");

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuarioExistente));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioExistente);

        Usuario usuarioActualizado = usuarioService.actualizar(1, nuevosDatos);

        assertNotNull(usuarioActualizado);
        assertEquals("Maria Actualizada", usuarioActualizado.getNombre());
        verify(usuarioRepository, times(1)).findById(1);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    public void testEliminar() {
        Usuario usuario = createUsuario();

        // El servicio de eliminar primero busca por ID y luego elimina
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        doNothing().when(usuarioRepository).delete(usuario);

        usuarioService.eliminar(1);

        verify(usuarioRepository, times(1)).findById(1);
        verify(usuarioRepository, times(1)).delete(usuario);
    }
}