package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario guardar(Usuario usuario) {
        usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
        return usuarioRepository.save(usuario);
    }

    public void eliminar(Integer id) {
        Usuario usuario = obtenerPorId(id);
        if (usuario != null) {
            usuarioRepository.delete(usuario);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    public Usuario actualizar(Integer id, Usuario usuario) {
        Usuario usuarioE = obtenerPorId(id);
        if (usuarioE != null) {
            if (usuario.getNombre() != null) usuarioE.setNombre(usuario.getNombre());
            if (usuario.getCorreo() != null) usuarioE.setCorreo(usuario.getCorreo());
            if (usuario.getContraseña() != null) usuarioE.setContraseña(usuario.getContraseña());
            return usuarioRepository.save(usuarioE);
        }
        return null;
    }

}
