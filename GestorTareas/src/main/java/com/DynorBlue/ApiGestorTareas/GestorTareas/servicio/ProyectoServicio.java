package com.DynorBlue.ApiGestorTareas.GestorTareas.servicio;

import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Proyecto;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Usuario;
import java.util.List;

public interface ProyectoServicio {

    // Crear
    Proyecto guardarProyecto(Proyecto proyecto);

    // Leer
    List<Proyecto> obtenerTodosLosProyectos();
    Proyecto obtenerProyectoPorId(Integer id);
    List<Proyecto> obtenerProyectosPorPropietario(Usuario usuario);
    List<Proyecto> obtenerProyectosPorPropietarioOrdenados(Usuario usuario); //  Nuevo método

    // Actualizar
    Proyecto actualizarProyecto(Proyecto proyecto);

    // Borrar
    void eliminarProyecto(Integer id);
}
