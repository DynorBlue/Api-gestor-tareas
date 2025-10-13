package com.DynorBlue.ApiGestorTareas.GestorTareas.repositorio;

import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Proyecto;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepositorio extends JpaRepository<Tarea, Integer> {

    List<Tarea> findAllByProyecto(Proyecto proyecto);

    // El nombre del método corregido para seguir la convención de Spring Data JPA
    List<Tarea> findByProyectoInOrderByPrioridadDesc(List<Proyecto> proyectos);
}