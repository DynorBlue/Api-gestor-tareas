package com.DynorBlue.ApiGestorTareas.GestorTareas.servicio;

import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Comentario;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Tarea;
import com.DynorBlue.ApiGestorTareas.GestorTareas.repositorio.ComentarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ComentarioServicioImpl implements ComentarioServicio {

    private final ComentarioRepositorio comentarioRepositorio;
    @Autowired
    public ComentarioServicioImpl(ComentarioRepositorio comentarioRepositorio){
        this.comentarioRepositorio = comentarioRepositorio;
    }
    @Override
    public List<Comentario> obtenerComentariosPorTarea(Tarea tarea) {
        return comentarioRepositorio.findAllByTarea(tarea);
    }
}
