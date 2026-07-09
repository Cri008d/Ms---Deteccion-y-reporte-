package com.example.demo.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.example.demo.model.Usuario;

public class UsuarioValidatorTest {

    // Método auxiliar para crear un usuario válido rápido
    private Usuario crearUsuarioValido() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Maria Lopez");
        usuario.setCorreo("maria@gmail.com");
        usuario.setContraseña("PasswordFuerte123@");
        return usuario;
    }

    @Test
    public void testUsuarioValidoPasaValidacion() {
        Usuario usuario = crearUsuarioValido();

        // Validamos que un usuario correcto no lance ninguna excepción
        assertDoesNotThrow(() -> {
            UsuarioValidator.validar(usuario);
        });
    }

    @Test
    public void testUsuarioNuloLanzaExcepcion() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UsuarioValidator.validar(null);
        });
        assertTrue(exception.getMessage().contains("El usuario no puede ser nulo"));
    }

    @Test
    public void testNombreInvalidoLanzaExcepcion() {
        Usuario usuario = crearUsuarioValido();
        usuario.setNombre("Al"); // Nombre muy corto (menos de 3 letras)

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UsuarioValidator.validar(usuario);
        });
        assertTrue(exception.getMessage().contains("mínimo de 3 caracteres"));
    }

    @Test
    public void testCorreoInvalidoLanzaExcepcion() {
        Usuario usuario = crearUsuarioValido();
        usuario.setCorreo("mariagmail.com"); // Falta el @

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UsuarioValidator.validar(usuario);
        });
        assertTrue(exception.getMessage().contains("terminar en '.com'"));
    }

    @Test
    public void testContrasenaInvalidaLanzaExcepcion() {
        Usuario usuario = crearUsuarioValido();
        usuario.setContraseña("débil"); // Contraseña sin mayúsculas, ni caracteres especiales, ni longitud

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UsuarioValidator.validar(usuario);
        });
        assertTrue(exception.getMessage().contains("mínimo de 8 caracteres"));
    }
}