package com.DynorBlue.ApiGestorTareas.GestorTareas.servicio;

import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Comentario;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Tarea;

import java.util.List;

public interface ComentarioServicio {
    List<Comentario> obtenerComentariosPorTarea(Tarea tarea);

}
