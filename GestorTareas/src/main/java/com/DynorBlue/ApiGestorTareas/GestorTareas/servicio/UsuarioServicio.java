package com.DynorBlue.ApiGestorTareas.GestorTareas.servicio;

import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Usuario;

import java.util.Optional;

public interface UsuarioServicio {

    // ✅ Crea un nuevo usuario (encripta contraseña dentro del servicio)
    Usuario registrar(String nombreUsuario, String email, String contrasena, String nombre);

    // ✅ Devuelve un usuario por su ID (lanza excepción si no existe)
    Usuario obtenerPorId(Integer idUsuario);

    // ✅ Actualiza solo los campos básicos: nombre y email
    Usuario actualizarBasico(Integer idUsuario, String nombre, String email);

    // ✅ Útil si luego usas autenticación
    Optional<Usuario> buscarPorUsuarioOEmail(String userOrEmail);
}
