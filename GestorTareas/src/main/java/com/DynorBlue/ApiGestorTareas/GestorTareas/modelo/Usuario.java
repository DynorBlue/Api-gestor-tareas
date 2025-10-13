package com.DynorBlue.ApiGestorTareas.GestorTareas.modelo;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "Usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(unique = true, nullable = false)
    private String nombreUsuario;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false)
    private String nombre;

    private boolean enabled = true;

    // Constructores, getters y setters


    public Usuario() {
    }

    public Usuario(String contrasena, String email, boolean enabled, String nombre, Integer idUsuario, String nombreUsuario) {
        this.contrasena = contrasena;
        this.email = email;
        this.enabled = enabled;
        this.nombre = nombre;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // En un caso simple, puedes devolver una lista de roles fijos, por ejemplo, "ROLE_USER".
        // Si tienes roles dinámicos, debes crearlos aquí.
        // Aquí se usa un rol fijo para simplificar el ejemplo.
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return contrasena; // Devuelve la contraseña encriptada
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Asume que la cuenta nunca expira. Puedes cambiar la lógica.
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Asume que la cuenta nunca se bloquea. Puedes cambiar la lógica.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Asume que las credenciales nunca expiran. Puedes cambiar la lógica.
    }

    @Override
    public boolean isEnabled() {
        return enabled; // Retorna el valor del campo `enabled`
    }
}