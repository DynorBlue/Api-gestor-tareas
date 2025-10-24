package com.DynorBlue.ApiGestorTareas.GestorTareas.repositorio;

import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Proyecto;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Tarea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TareaRepositorio extends JpaRepository<Tarea, Integer> {


    List<Tarea> findAllByProyecto(Proyecto proyecto);

    List<Tarea> findByProyectoInOrderByPrioridadDesc(List<Proyecto> proyectos);

    // 🔹 Buscar por proyecto y estado (ej. "Pendiente", "Concluida", etc.)
    List<Tarea> findByProyectoAndEstadoIgnoreCaseOrderByPrioridadDesc(Proyecto proyecto, String estado);

    // 🔹 Paginado para grandes listados
    Page<Tarea> findByProyecto_IdProyecto(Integer idProyecto, Pageable pageable);

    // 🔹 Contar tareas de un proyecto
    long countByProyecto_IdProyecto(Integer idProyecto);
}