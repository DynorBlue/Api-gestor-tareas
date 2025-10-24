package com.DynorBlue.ApiGestorTareas.GestorTareas.servicio;

import com.DynorBlue.ApiGestorTareas.GestorTareas.excepcion.OperacionInvalidaException;
import com.DynorBlue.ApiGestorTareas.GestorTareas.excepcion.RecursoNoEncontradoException;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Comentario;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Tarea;
import com.DynorBlue.ApiGestorTareas.GestorTareas.repositorio.ComentarioRepositorio;
import com.DynorBlue.ApiGestorTareas.GestorTareas.repositorio.TareaRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ComentarioServicioImpl implements ComentarioServicio {

    private final ComentarioRepositorio comentarioRepositorio;
    private final TareaRepositorio tareaRepositorio;

    public ComentarioServicioImpl(ComentarioRepositorio comentarioRepositorio, TareaRepositorio tareaRepositorio) {
        this.comentarioRepositorio = comentarioRepositorio;
        this.tareaRepositorio = tareaRepositorio;
    }

    // ------------------- CRUD ---------------------

    @Override
    public Comentario guardarComentario(Comentario comentario) {
        if (comentario.getComentario() == null || comentario.getComentario().isBlank()) {
            throw new OperacionInvalidaException("El comentario no puede estar vacío.");
        }
        if (comentario.getTarea() == null) {
            throw new OperacionInvalidaException("Debe asociarse una tarea al comentario.");
        }
        return comentarioRepositorio.save(comentario);
    }

    @Override
    @Transactional(readOnly = true)
    public Comentario obtenerComentarioPorId(Integer id) {
        return comentarioRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Comentario no encontrado."));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comentario> obtenerTodosComentarios() {
        return comentarioRepositorio.findAll();
    }

    @Override
    public Comentario actualizarComentario(Comentario comentario) {
        if (comentario.getIdComentario() == null) {
            throw new OperacionInvalidaException("El id del comentario es obligatorio para actualizar.");
        }

        Comentario existente = comentarioRepositorio.findById(comentario.getIdComentario())
                .orElseThrow(() -> new RecursoNoEncontradoException("Comentario no encontrado."));

        if (comentario.getComentario() != null && !comentario.getComentario().isBlank()) {
            existente.setComentario(comentario.getComentario());
        }

        if (comentario.getTarea() != null) {
            existente.setTarea(comentario.getTarea());
        }

        return comentarioRepositorio.save(existente);
    }

    @Override
    public void eliminarComentario(Integer id) {
        if (!comentarioRepositorio.existsById(id)) {
            throw new RecursoNoEncontradoException("Comentario no encontrado.");
        }
        comentarioRepositorio.deleteById(id);
    }

    // ------------------- Personalizados ---------------------

    @Override
    @Transactional(readOnly = true)
    public List<Comentario> obtenerComentariosPorTarea(Tarea tarea) {
        if (tarea == null || tarea.getIdTarea() == null) {
            throw new OperacionInvalidaException("Debe especificarse una tarea válida.");
        }

        // Verificar que la tarea exista
        tareaRepositorio.findById(tarea.getIdTarea())
                .orElseThrow(() -> new RecursoNoEncontradoException("Tarea no encontrada."));

        // Traer comentarios más recientes primero
        return comentarioRepositorio.findByTareaOrderByFechaComentarioDesc(tarea);
    }

    @Override
    public Comentario actualizarComentarioPorId(Integer id, Comentario data) {
        // 1) Cargar existente o 404
        Comentario existente = comentarioRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Comentario no encontrado."));

        // 2) Actualizar campos permitidos
        if (data.getComentario() != null) {
            String texto = data.getComentario().trim();
            if (texto.isEmpty()) {
                throw new OperacionInvalidaException("El comentario no puede estar vacío.");
            }
            existente.setComentario(texto);
        }

        // (Opcional) permitir cambiar la tarea asociada si viene una válida
        if (data.getTarea() != null && data.getTarea().getIdTarea() != null) {
            var tarea = tareaRepositorio.findById(data.getTarea().getIdTarea())
                    .orElseThrow(() -> new RecursoNoEncontradoException("Tarea destino no encontrada."));
            existente.setTarea(tarea);
        }

        // 3) Guardar y devolver
        return comentarioRepositorio.save(existente);
    }
}
