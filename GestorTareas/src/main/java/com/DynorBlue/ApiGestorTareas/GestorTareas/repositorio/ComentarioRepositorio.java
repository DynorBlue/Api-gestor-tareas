package com.DynorBlue.ApiGestorTareas.GestorTareas.repositorio;

import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Comentario;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepositorio  extends JpaRepository<Comentario, Integer> {
    List<Comentario>findAllByTarea(Tarea tarea);
}
