package com.DynorBlue.ApiGestorTareas.GestorTareas.servicio;

import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Proyecto;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Usuario;

import java.util.List;

public interface ProyectoServicio {
    List<Proyecto> obtenerProyectosPorPropietario(Usuario servicio);
}
