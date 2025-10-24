package com.DynorBlue.ApiGestorTareas.GestorTareas.servicio;

import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Proyecto;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Tarea;

import java.util.List;

public interface TareaServicio {

    // CRUD básico
    Tarea guardarTarea(Tarea tarea);

    // 👇 ESTE MÉTODO DEBE EXISTIR
    Tarea obtenerTareaPorId(Integer id);

    List<Tarea> obtenerTodasTareas();

    Tarea actualizarTarea(Tarea tarea);

    void eliminarTarea(Integer id);

    // Personalizados
    List<Tarea> obtenerTareasPorProyecto(Proyecto proyecto);

    List<Tarea> obtenerTareasPrioridad(List<Proyecto> proyectos);

}
