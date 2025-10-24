package com.DynorBlue.ApiGestorTareas.GestorTareas.repositorio;


import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Proyecto;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProyectoRepositorio extends JpaRepository<Proyecto, Integer> {

    // Listado simple ordenado por nombre
    List<Proyecto> findByUsuarioOrderByNombreProyectoAsc(Usuario usuario);

    // Paginado por dueño (por id) — evita tener que cargar el Usuario completo
    Page<Proyecto> findByUsuario_IdUsuario(Integer idUsuario, Pageable pageable);

    // Evitar nombres repetidos para un mismo usuario (case-insensitive)
    boolean existsByUsuario_IdUsuarioAndNombreProyectoIgnoreCase(Integer idUsuario, String nombreProyecto);

    // Búsqueda por texto en el nombre del proyecto (case-insensitive)
    Page<Proyecto> findByUsuario_IdUsuarioAndNombreProyectoContainingIgnoreCase(Integer idUsuario, String q, Pageable pageable);

    // Asegurar que un proyecto pertenece al usuario (útil para verificar acceso)
    Optional<Proyecto> findByIdProyectoAndUsuario_IdUsuario(Integer idProyecto, Integer idUsuario);

    // Cuando sí necesites el Usuario ya cargado (evita N+1)
    @EntityGraph(attributePaths = "usuario")
    Page<Proyecto> findByUsuario_IdUsuarioOrderByNombreProyectoAsc(Integer idUsuario, Pageable pageable);
}