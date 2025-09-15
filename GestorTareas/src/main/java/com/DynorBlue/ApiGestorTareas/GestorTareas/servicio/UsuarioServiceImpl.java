package com.DynorBlue.ApiGestorTareas.GestorTareas.servicio;

import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Usuario;
import com.DynorBlue.ApiGestorTareas.GestorTareas.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UsuarioServiceImpl implements UsuarioServicio{

    private final UsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepositorio usuarioRepositorio, PasswordEncoder passwordEncoder) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return usuarioRepositorio.save(usuario);
    }

    @Override
    public Optional<Usuario> findByNombreUsuario(String nombreUsuario) {
        return usuarioRepositorio.findByNombreUsuario(nombreUsuario);
    }
}
