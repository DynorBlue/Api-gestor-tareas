package com.DynorBlue.ApiGestorTareas.GestorTareas.controlador;

import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Proyecto;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Tarea;
import com.DynorBlue.ApiGestorTareas.GestorTareas.servicio.TareaServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaControlador {

    private final TareaServicio tareaServicio;

    public TareaControlador(TareaServicio tareaServicio) {
        this.tareaServicio = tareaServicio;
    }

    // ✅ Crear
    @PostMapping
    public ResponseEntity<Tarea> crearTarea(@RequestBody Tarea tarea) {
        Tarea nueva = tareaServicio.guardarTarea(tarea);
        return ResponseEntity.ok(nueva);
    }

    // ✅ Listar todas
    @GetMapping
    public ResponseEntity<List<Tarea>> obtenerTodas() {
        List<Tarea> lista = tareaServicio.obtenerTodasTareas();
        return ResponseEntity.ok(lista);
    }

    // ✅ Obtener por ID
    @GetMapping("/{idTarea}")
    public ResponseEntity<Tarea> obtenerPorId(@PathVariable Integer idTarea) {
        Tarea tarea = tareaServicio.obtenerTareaPorId(idTarea);
        if (tarea != null) {
            return ResponseEntity.ok(tarea);
        }
        return ResponseEntity.notFound().build();
    }

    // ✅ Actualizar
    @PutMapping("/{idTarea}")
    public ResponseEntity<Tarea> actualizarTarea(@PathVariable Integer idTarea, @RequestBody Tarea body) {
        // Reutilizamos tu método existente actualizarTarea()
        body.setIdTarea(idTarea);
        Tarea actualizada = tareaServicio.actualizarTarea(body);
        return ResponseEntity.ok(actualizada);
    }

    // ✅ Eliminar
    @DeleteMapping("/{idTarea}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer idTarea) {
        tareaServicio.eliminarTarea(idTarea);
        return ResponseEntity.noContent().build();
    }

    // ✅ Obtener tareas por proyecto
    @GetMapping("/proyecto/{idProyecto}")
    public ResponseEntity<List<Tarea>> obtenerPorProyecto(@PathVariable Integer idProyecto) {
        Proyecto proyecto = new Proyecto();
        proyecto.setIdProyecto(idProyecto);
        List<Tarea> lista = tareaServicio.obtenerTareasPorProyecto(proyecto);
        return ResponseEntity.ok(lista);
    }

    // ✅ Obtener tareas de varios proyectos por prioridad
    @PostMapping("/prioridad")
    public ResponseEntity<List<Tarea>> obtenerPorPrioridad(@RequestBody List<Proyecto> proyectos) {
        List<Tarea> lista = tareaServicio.obtenerTareasPrioridad(proyectos);
        return ResponseEntity.ok(lista);
    }
}
