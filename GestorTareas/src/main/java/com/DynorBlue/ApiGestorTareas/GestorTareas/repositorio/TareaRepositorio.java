package com.DynorBlue.ApiGestorTareas.GestorTareas.repositorio;

import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Proyecto;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Tarea;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TareaRepositorio extends JpaRepository<Tarea, Integer> {
    List<Tarea> findAllByProyecto(Proyecto Proyecto);
    List<Tarea> findByProyectoInOrderByPrioridadDesc(List<Proyecto> proyectos);
}