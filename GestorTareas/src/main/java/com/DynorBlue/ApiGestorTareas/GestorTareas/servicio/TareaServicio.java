package com.DynorBlue.ApiGestorTareas.GestorTareas.servicio;

import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Proyecto;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Tarea;

import java.util.List;

public interface TareaServicio {
    List<Tarea> obtenerTodasTareas(Proyecto Proyecto);
    List<Tarea> obtenerTareasPrioridad(List<Proyecto> proyectos);
}
