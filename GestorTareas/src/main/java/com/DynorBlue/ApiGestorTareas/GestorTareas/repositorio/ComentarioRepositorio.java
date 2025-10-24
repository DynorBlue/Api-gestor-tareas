package com.DynorBlue.ApiGestorTareas.GestorTareas.repositorio;

import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Comentario;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Tarea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepositorio extends JpaRepository<Comentario, Integer> {

    // Todos los comentarios de una tarea (sin orden)
    List<Comentario> findAllByTarea(Tarea tarea);

    // Ordenados del más reciente al más antiguo
    List<Comentario> findByTareaOrderByFechaComentarioDesc(Tarea tarea);

    // Paginado (para interfaces grandes)
    Page<Comentario> findByTarea_IdTareaOrderByFechaComentarioDesc(Integer idTarea, Pageable pageable);

    // Contar comentarios de una tarea
    long countByTarea_IdTarea(Integer idTarea);
}