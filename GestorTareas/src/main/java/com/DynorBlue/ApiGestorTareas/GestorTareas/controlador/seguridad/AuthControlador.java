package com.DynorBlue.ApiGestorTareas.GestorTareas.controlador.seguridad;

import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Usuario;
import com.DynorBlue.ApiGestorTareas.GestorTareas.repositorio.UsuarioRepositorio;
import com.DynorBlue.ApiGestorTareas.GestorTareas.seguridad.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

record LoginRequest(String username, String password){}
record TokenResponse(String token){}

@RestController
@RequestMapping("/auth")
public class AuthControlador {

    private final UsuarioRepositorio usuarioRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthControlador(UsuarioRepositorio usuarioRepo, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioRepo = usuarioRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        Usuario u = usuarioRepo.findByNombreUsuario(req.username()).orElse(null);
        if (u == null || !passwordEncoder.matches(req.password(), u.getPassword())) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
        String token = jwtService.generate(u.getUsername());
        return ResponseEntity.ok(new TokenResponse(token));
    }
}
