package com.DynorBlue.ApiGestorTareas.GestorTareas.servicio;

import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Proyecto;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Usuario;
import com.DynorBlue.ApiGestorTareas.GestorTareas.repositorio.ProyectoRepositorio;
import com.DynorBlue.ApiGestorTareas.GestorTareas.servicio.ProyectoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoServicioImpl implements ProyectoServicio {

    private final ProyectoRepositorio proyectoRepositorio;

    @Autowired
    public ProyectoServicioImpl(ProyectoRepositorio proyectoRepositorio) {
        this.proyectoRepositorio = proyectoRepositorio;
    }

    @Override
    public Proyecto guardarProyecto(Proyecto proyecto) {
        return proyectoRepositorio.save(proyecto);
    }

    @Override
    public List<Proyecto> obtenerTodosLosProyectos() {
        return proyectoRepositorio.findAll();
    }

    @Override
    public Proyecto obtenerProyectoPorId(Integer id) {
        Optional<Proyecto> proyecto = proyectoRepositorio.findById(id);
        return proyecto.orElse(null);
    }

    @Override
    public List<Proyecto> obtenerProyectosPorPropietario(Usuario usuario) {
        return proyectoRepositorio.findByUsuario(usuario);
    }

    @Override
    public List<Proyecto> obtenerProyectosPorPropietarioOrdenados(Usuario usuario) {
        return proyectoRepositorio.findByUsuarioOrderByNombreProyectoAsc(usuario);
    }

    @Override
    public Proyecto actualizarProyecto(Proyecto proyecto) {
        return proyectoRepositorio.save(proyecto);
    }

    @Override
    public void eliminarProyecto(Integer id) {
        proyectoRepositorio.deleteById(id);
    }
}