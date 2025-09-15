package com.DynorBlue.ApiGestorTareas.GestorTareas.servicio;

import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Usuario;

import java.util.Optional;

public interface UsuarioServicio {
    Usuario registrarUsuario(Usuario usuario);
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

}
