package com.DynorBlue.ApiGestorTareas.GestorTareas.controlador;

import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Comentario;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Tarea;
import com.DynorBlue.ApiGestorTareas.GestorTareas.servicio.ComentarioServicio;
import com.DynorBlue.ApiGestorTareas.GestorTareas.servicio.TareaServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioControlador {

    private final ComentarioServicio comentarioServicio;
    private final TareaServicio tareaServicio;

    public ComentarioControlador(ComentarioServicio comentarioServicio, TareaServicio tareaServicio) {
        this.comentarioServicio = comentarioServicio;
        this.tareaServicio = tareaServicio;
    }

    // Crear comentario asociado a una tarea
    @PostMapping("/tarea/{tareaId}")
    public ResponseEntity<Comentario> crearComentario(@PathVariable Integer tareaId,
                                                      @Valid @RequestBody Comentario comentario) {
        // Si la tarea no existe, el servicio lanzará RecursoNoEncontradoException -> 404 por tu Advice
        Tarea tarea = tareaServicio.obtenerTareaPorId(tareaId);
        comentario.setTarea(tarea);
        Comentario creado = comentarioServicio.guardarComentario(comentario);
        return ResponseEntity.ok(creado);
    }

    // Obtener comentario por id
    @GetMapping("/{id}")
    public ResponseEntity<Comentario> obtenerComentarioPorId(@PathVariable Integer id) {
        Comentario c = comentarioServicio.obtenerComentarioPorId(id);
        return ResponseEntity.ok(c);
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<Comentario>> obtenerTodosComentarios() {
        return ResponseEntity.ok(comentarioServicio.obtenerTodosComentarios());
    }

    // Listar por tarea (recientes primero si tu servicio usa findByTareaOrderByFechaComentarioDesc)
    @GetMapping("/tarea/{tareaId}")
    public ResponseEntity<List<Comentario>> obtenerComentariosPorTarea(@PathVariable Integer tareaId) {
        Tarea tarea = tareaServicio.obtenerTareaPorId(tareaId);
        List<Comentario> lista = comentarioServicio.obtenerComentariosPorTarea(tarea);
        return ResponseEntity.ok(lista);
    }

    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Comentario> actualizarComentario(@PathVariable Integer id,
                                                           @RequestBody Comentario comentario) {
        Comentario actualizado = comentarioServicio.actualizarComentarioPorId(id, comentario);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarComentario(@PathVariable Integer id) {
        comentarioServicio.eliminarComentario(id);
        return ResponseEntity.noContent().build();
    }
}
