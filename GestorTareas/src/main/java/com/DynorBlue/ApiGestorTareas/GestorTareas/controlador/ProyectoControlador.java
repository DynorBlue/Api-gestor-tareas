package com.DynorBlue.ApiGestorTareas.GestorTareas.controlador;

import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Proyecto;
import com.DynorBlue.ApiGestorTareas.GestorTareas.servicio.ProyectoServicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoControlador {

    private final ProyectoServicio proyectoServicio;

    public ProyectoControlador(ProyectoServicio proyectoServicio) {
        this.proyectoServicio = proyectoServicio;
    }

    // Crear: requiere idUsuario
    @PostMapping
    public ResponseEntity<Proyecto> crearProyecto(
            @RequestParam Integer idUsuario,
            @RequestBody Proyecto body) {
        var creado = proyectoServicio.crear(idUsuario, body.getNombreProyecto(), body.getDescripcion());
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    // Listar por usuario (paginado)
    @GetMapping
    public ResponseEntity<Page<Proyecto>> listarPorUsuario(
            @RequestParam Integer idUsuario,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        var result = proyectoServicio.listarPorUsuario(idUsuario, PageRequest.of(page, size));
        return ResponseEntity.ok(result);
    }

    // Obtener un proyecto que pertenezca al usuario
    @GetMapping("/{idProyecto}")
    public ResponseEntity<Proyecto> obtenerProyecto(
            @PathVariable Integer idProyecto,
            @RequestParam Integer idUsuario
    ) {
        var p = proyectoServicio.obtenerDeUsuario(idProyecto, idUsuario);
        return ResponseEntity.ok(p);
    }

    // Actualizar nombre/descripcion
    @PutMapping("/{idProyecto}")
    public ResponseEntity<Proyecto> actualizarProyecto(
            @PathVariable Integer idProyecto,
            @RequestParam Integer idUsuario,
            @RequestBody Proyecto body
    ) {
        var actualizado = proyectoServicio.actualizar(
                idProyecto,
                idUsuario,
                body.getNombreProyecto(),
                body.getDescripcion()
        );
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar
    @DeleteMapping("/{idProyecto}")
    public ResponseEntity<Void> eliminarProyecto(
            @PathVariable Integer idProyecto,
            @RequestParam Integer idUsuario
    ) {
        proyectoServicio.eliminar(idProyecto, idUsuario);
        return ResponseEntity.noContent().build();
    }
}
