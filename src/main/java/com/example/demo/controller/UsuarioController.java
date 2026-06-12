package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        List<Usuario> usuarios = usuarioService.obtenerTodos();
        if (usuarios.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Integer id) {
        Usuario usuario = usuarioService.obtenerPorId(id);
        return (usuario != null) ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @PostMapping("/registrar") 
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.guardar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @PostMapping("/login") // Escucha en: /api/v1/usuarios/login
    public ResponseEntity<?> login(@RequestBody Usuario loginRequest) {
        Usuario usuarioValido = usuarioService.obtenerTodos().stream()
                .filter(u -> u.getCorreo().equalsIgnoreCase(loginRequest.getCorreo()))
                .findFirst()
                .orElse(null);

        if (usuarioValido == null || !usuarioValido.getContraseña().equals(loginRequest.getContraseña())) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Credenciales inválidas. Correo o contraseña incorrectos.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }

        String tokenGenerado = "SESSION_TOKEN_" + UUID.randomUUID().toString().replace("-", "") + "_" + usuarioValido.getIdUsuario();

        Map<String, String> successResponse = new HashMap<>();
        successResponse.put("token", tokenGenerado);
        successResponse.put("nombre", usuarioValido.getNombre());
        successResponse.put("correo", usuarioValido.getCorreo());

        // Devolvemos el JSON con código 200 OK
        return ResponseEntity.ok(successResponse);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> reemplazar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        Usuario usuarioAct = usuarioService.actualizar(id, usuario);
        return (usuarioAct != null) ? ResponseEntity.ok(usuarioAct) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
