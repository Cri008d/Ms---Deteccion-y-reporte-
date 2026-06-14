package com.example.demo.validator;

import com.example.demo.model.Usuario;
import org.springframework.util.Assert;

public class UsuarioValidator {

    public static void validar(Usuario usuario) {
        Assert.notNull(usuario, "El usuario no puede ser nulo");

        // Validación del Nombre: Solo letras, mínimo 3 caracteres
        Assert.notNull(usuario.getNombre(), "El nombre es obligatorio.");
        Assert.isTrue(usuario.getNombre().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{3,}$"), 
            "El nombre debe contener solo letras y tener un mínimo de 3 caracteres.");

        // Validación del Correo: Debe contener '@' y terminar obligatoriamente en '.com'
        Assert.notNull(usuario.getCorreo(), "El correo es obligatorio.");
        Assert.isTrue(usuario.getCorreo().contains("@") && usuario.getCorreo().endsWith(".com"), 
            "El formato del correo es inválido. Debe contener un '@' y terminar en '.com'.");

        // Validación de la Contraseña: Min 8 caracteres, 1 mayúscula, 1 carácter especial
        Assert.notNull(usuario.getContraseña(), "La contraseña es obligatoria.");
        Assert.isTrue(usuario.getContraseña().length() >= 8, 
            "La contraseña debe tener un mínimo de 8 caracteres.");
        Assert.isTrue(usuario.getContraseña().matches(".*[A-Z].*"), 
            "La contraseña debe contener al menos una letra mayúscula.");
        Assert.isTrue(usuario.getContraseña().matches(".*[^a-zA-Z0-9].*"), 
            "La contraseña debe contener al menos un carácter especial.");
    }
}