package com.DynorBlue.ApiGestorTareas.GestorTareas.servicio;

import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Proyecto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProyectoServicio {
    Proyecto crear(Integer idUsuario, String nombreProyecto, String descripcion);
    Page<Proyecto> listarPorUsuario(Integer idUsuario, Pageable pageable);
    Proyecto obtenerDeUsuario(Integer idProyecto, Integer idUsuario);
    Proyecto actualizar(Integer idProyecto, Integer idUsuario, String nuevoNombre, String nuevaDescripcion);
    void eliminar(Integer idProyecto, Integer idUsuario);
}
