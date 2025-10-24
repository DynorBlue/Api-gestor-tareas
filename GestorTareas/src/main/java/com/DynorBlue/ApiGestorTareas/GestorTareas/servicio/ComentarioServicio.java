package com.DynorBlue.ApiGestorTareas.GestorTareas.servicio;

import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Comentario;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Tarea;

import java.util.List;

public interface ComentarioServicio {

    // CRUD
    Comentario guardarComentario(Comentario comentario);
    Comentario obtenerComentarioPorId(Integer id);
    List<Comentario> obtenerTodosComentarios();
    Comentario actualizarComentario(Comentario comentario);
    void eliminarComentario(Integer id);

    // Personalizado: comentarios de una tarea
    List<Comentario> obtenerComentariosPorTarea(Tarea tarea);
    Comentario actualizarComentarioPorId(Integer id, Comentario data);
}
