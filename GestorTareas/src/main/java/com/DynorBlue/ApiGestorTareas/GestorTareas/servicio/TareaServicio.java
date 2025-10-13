package com.DynorBlue.ApiGestorTareas.GestorTareas.servicio;

import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Proyecto;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Tarea;

import java.util.List;

public interface TareaServicio {

    // CRUD
    Tarea guardarTarea(Tarea tarea);
    Tarea obtenerTareaPorId(Integer id);
    List<Tarea> obtenerTodasTareas();
    Tarea actualizarTarea(Tarea tarea);
    void eliminarTarea(Integer id);

    // Métodos personalizados
    List<Tarea> obtenerTareasPorProyecto(Proyecto proyecto);
    List<Tarea> obtenerTareasPrioridad(List<Proyecto> proyectos);
}
