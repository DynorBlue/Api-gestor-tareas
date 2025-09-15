package com.DynorBlue.ApiGestorTareas.GestorTareas.servicio;

import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Proyecto;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Tarea;
import com.DynorBlue.ApiGestorTareas.GestorTareas.repositorio.TareaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TareaServicioImpl implements TareaServicio{

    private final TareaRepositorio tareaRepositorio;

    @Autowired
    public TareaServicioImpl(TareaRepositorio tareaRepositorio){
        this.tareaRepositorio = tareaRepositorio;
    }
    @Override
    public List<Tarea> obtenerTodasTareas(Proyecto proyecto) {
        return tareaRepositorio.findAllByProyecto(proyecto);
    }

    @Override
    public List<Tarea> obtenerTareasPrioridad(List<Proyecto> proyectos) {
        return tareaRepositorio.findByProyectoInOrderByPrioridadDesc(proyectos);

    }
}
