package com.DynorBlue.ApiGestorTareas.GestorTareas.controlador;

import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Proyecto;
import com.DynorBlue.ApiGestorTareas.GestorTareas.servicio.ProyectoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoControlador {

    @Autowired
    private ProyectoServicio proyectoServicio;

    @PostMapping
    public ResponseEntity<Proyecto> crearProyecto(@RequestBody Proyecto proyecto){
        Proyecto nuevoProyecto = proyectoServicio.guardarProyecto(proyecto);
        return new ResponseEntity<>(nuevoProyecto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Proyecto>> obtenerTodosLosProyectos() {
        List<Proyecto> proyectos = proyectoServicio.obtenerTodosLosProyectos();
        return new ResponseEntity<>(proyectos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> obtenerProyectoPorId(@PathVariable Integer id) {
        Proyecto proyecto = proyectoServicio.obtenerProyectoPorId(id);
        if (proyecto != null) {
            return new ResponseEntity<>(proyecto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proyecto> actualizarProyecto(@PathVariable Integer id, @RequestBody Proyecto proyectoActualizado) {
        Proyecto proyectoExistente = proyectoServicio.obtenerProyectoPorId(id);
        if (proyectoExistente != null) {
            proyectoExistente.setNombreProyecto(proyectoActualizado.getNombreProyecto());
            proyectoExistente.setDescripcion(proyectoActualizado.getDescripcion());
            proyectoExistente.setUsario(proyectoActualizado.getUsario());
            // Si la fecha de creación se actualiza
            proyectoExistente.setFechaCreacion(proyectoActualizado.getFechaCreacion());

            Proyecto proyectoModificado = proyectoServicio.actualizarProyecto(proyectoExistente);
            return new ResponseEntity<>(proyectoModificado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProyecto(@PathVariable Integer id) {
        Proyecto proyectoExistente = proyectoServicio.obtenerProyectoPorId(id);
        if (proyectoExistente != null) {
            proyectoServicio.eliminarProyecto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}