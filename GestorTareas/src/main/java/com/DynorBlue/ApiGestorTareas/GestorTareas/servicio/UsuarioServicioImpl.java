package com.DynorBlue.ApiGestorTareas.GestorTareas.servicio;

import com.DynorBlue.ApiGestorTareas.GestorTareas.excepcion.OperacionInvalidaException;
import com.DynorBlue.ApiGestorTareas.GestorTareas.excepcion.RecursoNoEncontradoException;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Usuario;
import com.DynorBlue.ApiGestorTareas.GestorTareas.repositorio.UsuarioRepositorio;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepositorio usuarioRepo;
    private final PasswordEncoder passwordEncoder;

    // ✅ Constructor con inyección automática
    public UsuarioServicioImpl(UsuarioRepositorio usuarioRepo, PasswordEncoder passwordEncoder) {
        this.usuarioRepo = usuarioRepo;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ Registrar usuario nuevo
    @Override
    public Usuario registrar(String nombreUsuario, String email, String contrasena, String nombre) {
        if (usuarioRepo.existsByNombreUsuario(nombreUsuario)) {
            throw new OperacionInvalidaException("El nombre de usuario ya existe.");
        }
        if (usuarioRepo.existsByEmail(email)) {
            throw new OperacionInvalidaException("El email ya está registrado.");
        }

        Usuario u = new Usuario();
        u.setNombreUsuario(nombreUsuario);
        u.setEmail(email);
        u.setContrasena(passwordEncoder.encode(contrasena)); // 🔒 Encriptación aquí
        u.setNombre(nombre);
        u.setEnabled(true);
        return usuarioRepo.save(u);
    }

    // ✅ Obtener usuario por ID
    @Override
    @Transactional(readOnly = true)
    public Usuario obtenerPorId(Integer idUsuario) {
        return usuarioRepo.findById(idUsuario)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado."));
    }

    // ✅ Actualizar solo nombre y email
    @Override
    public Usuario actualizarBasico(Integer idUsuario, String nombre, String email) {
        Usuario existente = obtenerPorId(idUsuario);

        if (email != null && !email.isBlank() && !email.equalsIgnoreCase(existente.getEmail())) {
            if (usuarioRepo.existsByEmail(email)) {
                throw new OperacionInvalidaException("El email ya está registrado por otro usuario.");
            }
            existente.setEmail(email);
        }
        if (nombre != null && !nombre.isBlank()) {
            existente.setNombre(nombre);
        }

        return usuarioRepo.save(existente);
    }

    // ✅ Buscar por nombre de usuario o email
    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorUsuarioOEmail(String userOrEmail) {
        return usuarioRepo.findByNombreUsuarioOrEmail(userOrEmail, userOrEmail);
    }
}
