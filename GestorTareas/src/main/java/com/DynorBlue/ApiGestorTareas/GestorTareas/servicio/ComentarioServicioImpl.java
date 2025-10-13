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
    public ComentarioServicioImpl(ComentarioRepositorio comentarioRepositorio) {
        this.comentarioRepositorio = comentarioRepositorio;
    }

    @Override
    public Comentario guardarComentario(Comentario comentario) {
        return comentarioRepositorio.save(comentario);
    }

    @Override
    public Comentario obtenerComentarioPorId(Integer id) {
        return comentarioRepositorio.findById(id).orElse(null);
        // ⚠️ Si quieres, aquí mejor lanza una excepción personalizada
    }

    @Override
    public List<Comentario> obtenerTodosComentarios() {
        return comentarioRepositorio.findAll();
    }

    @Override
    public Comentario actualizarComentario(Comentario comentario) {
        return comentarioRepositorio.save(comentario);
    }

    @Override
    public void eliminarComentario(Integer id) {
        comentarioRepositorio.deleteById(id);
    }

    @Override
    public List<Comentario> obtenerComentariosPorTarea(Tarea tarea) {
        return comentarioRepositorio.findAllByTarea(tarea);
    }
}
