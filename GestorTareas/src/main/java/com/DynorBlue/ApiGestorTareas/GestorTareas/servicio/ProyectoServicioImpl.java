package com.DynorBlue.ApiGestorTareas.GestorTareas.servicio;

import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Proyecto;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Usuario;
import com.DynorBlue.ApiGestorTareas.GestorTareas.repositorio.ProyectoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProyectoServicioImpl implements ProyectoServicio {

    private final ProyectoRepositorio proyectoRepositorio;

    @Autowired
    public ProyectoServicioImpl(ProyectoRepositorio proyectoRepositorio) {
        this.proyectoRepositorio = proyectoRepositorio;
    }

    @Override
    public List<Proyecto> obtenerProyectosPorPropietario(Usuario usuario) {
        // Llama al método del repositorio para obtener los proyectos de un propietario específico
        return proyectoRepositorio.findAllOrderByUsuarioAsc(usuario);
    }
}
