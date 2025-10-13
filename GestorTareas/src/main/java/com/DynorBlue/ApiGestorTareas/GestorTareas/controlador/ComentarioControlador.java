package com.DynorBlue.ApiGestorTareas.GestorTareas.controlador;

import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Comentario;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Tarea;
import com.DynorBlue.ApiGestorTareas.GestorTareas.servicio.ComentarioServicio;
import com.DynorBlue.ApiGestorTareas.GestorTareas.servicio.TareaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ComentarioControlador {

    private final ComentarioServicio comentarioServicio;
    private final TareaServicio tareaServicio;

    @Autowired
    public ComentarioControlador(ComentarioServicio comentarioServicio, TareaServicio tareaServicio) {
        this.comentarioServicio = comentarioServicio;
        this.tareaServicio = tareaServicio;
    }

    // Crear comentario asociado a una tarea
    @PostMapping("/tarea/{tareaId}")
    public ResponseEntity<Comentario> crearComentario(@PathVariable Integer tareaId, @RequestBody Comentario comentario) {
        Tarea tarea = tareaServicio.obtenerTareaPorId(tareaId);
        if (tarea == null) {
            return ResponseEntity.notFound().build();
        }
        comentario.setTarea(tarea);
        return ResponseEntity.ok(comentarioServicio.guardarComentario(comentario));
    }

    // Obtener comentario por id
    @GetMapping("/{id}")
    public ResponseEntity<Comentario> obtenerComentarioPorId(@PathVariable Integer id) {
        Comentario comentario = comentarioServicio.obtenerComentarioPorId(id);
        return comentario != null ? ResponseEntity.ok(comentario) : ResponseEntity.notFound().build();
    }

    // Obtener todos los comentarios
    @GetMapping
    public List<Comentario> obtenerTodosComentarios() {
        return comentarioServicio.obtenerTodosComentarios();
    }

    // Obtener comentarios de una tarea específica
    @GetMapping("/tarea/{tareaId}")
    public ResponseEntity<List<Comentario>> obtenerComentariosPorTarea(@PathVariable Integer tareaId) {
        Tarea tarea = tareaServicio.obtenerTareaPorId(tareaId);
        if (tarea == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comentarioServicio.obtenerComentariosPorTarea(tarea));
    }

    // Actualizar comentario
    @PutMapping("/{id}")
    public ResponseEntity<Comentario> actualizarComentario(@PathVariable Integer id, @RequestBody Comentario comentario) {
        Comentario existente = comentarioServicio.obtenerComentarioPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        comentario.setIdComentario(id);
        return ResponseEntity.ok(comentarioServicio.actualizarComentario(comentario));
    }

    // Eliminar comentario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarComentario(@PathVariable Integer id) {
        Comentario comentario = comentarioServicio.obtenerComentarioPorId(id);
        if (comentario == null) {
            return ResponseEntity.notFound().build();
        }
        comentarioServicio.eliminarComentario(id);
        return ResponseEntity.noContent().build();
    }
}
