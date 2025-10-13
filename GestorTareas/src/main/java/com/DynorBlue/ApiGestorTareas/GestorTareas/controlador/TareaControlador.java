package com.DynorBlue.ApiGestorTareas.GestorTareas.controlador;

import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Proyecto;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Tarea;
import com.DynorBlue.ApiGestorTareas.GestorTareas.servicio.TareaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaControlador {

    private final TareaServicio tareaServicio;

    @Autowired
    public TareaControlador(TareaServicio tareaServicio) {
        this.tareaServicio = tareaServicio;
    }

    // ✅ Crear tarea
    @PostMapping
    public Tarea crearTarea(@RequestBody Tarea tarea) {
        return tareaServicio.guardarTarea(tarea);
    }

    // ✅ Obtener todas las tareas
    @GetMapping
    public List<Tarea> obtenerTodasTareas() {
        return tareaServicio.obtenerTodasTareas();
    }

    // ✅ Obtener tarea por ID
    @GetMapping("/{id}")
    public Tarea obtenerTareaPorId(@PathVariable Integer id) {
        return tareaServicio.obtenerTareaPorId(id);
    }

    // ✅ Actualizar tarea
    @PutMapping("/{id}")
    public Tarea actualizarTarea(@PathVariable Integer id, @RequestBody Tarea tarea) {
        tarea.setIdTarea(id); // asegúrate de que la entidad Tarea tenga el campo "id"
        return tareaServicio.actualizarTarea(tarea);
    }

    // ✅ Eliminar tarea
    @DeleteMapping("/{id}")
    public void eliminarTarea(@PathVariable Integer id) {
        tareaServicio.eliminarTarea(id);
    }

    // ✅ Obtener tareas de un proyecto
    @GetMapping("/proyecto/{idProyecto}")
    public List<Tarea> obtenerTareasPorProyecto(@PathVariable Integer idProyecto) {
        Proyecto proyecto = new Proyecto();
        proyecto.setIdProyecto(idProyecto); // asumimos que Proyecto tiene campo "id"
        return tareaServicio.obtenerTareasPorProyecto(proyecto);
    }

    // ✅ Obtener tareas de varios proyectos ordenadas por prioridad DESC
    @PostMapping("/prioridad")
    public List<Tarea> obtenerTareasPorPrioridad(@RequestBody List<Proyecto> proyectos) {
        return tareaServicio.obtenerTareasPrioridad(proyectos);
    }
}
