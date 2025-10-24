package com.DynorBlue.ApiGestorTareas.GestorTareas.servicio;

import com.DynorBlue.ApiGestorTareas.GestorTareas.excepcion.OperacionInvalidaException;
import com.DynorBlue.ApiGestorTareas.GestorTareas.excepcion.RecursoNoEncontradoException;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Proyecto;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Usuario;
import com.DynorBlue.ApiGestorTareas.GestorTareas.repositorio.ProyectoRepositorio;
import com.DynorBlue.ApiGestorTareas.GestorTareas.repositorio.UsuarioRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProyectoServicioImpl implements ProyectoServicio {

    private final ProyectoRepositorio proyectoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;

    public ProyectoServicioImpl(ProyectoRepositorio proyectoRepositorio, UsuarioRepositorio usuarioRepositorio) {
        this.proyectoRepositorio = proyectoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public Proyecto crear(Integer idUsuario, String nombreProyecto, String descripcion) {
        Usuario u = usuarioRepositorio.findById(idUsuario)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado"));
        if (proyectoRepositorio.existsByUsuario_IdUsuarioAndNombreProyectoIgnoreCase(idUsuario, nombreProyecto)) {
            throw new OperacionInvalidaException("Ya existe un proyecto con ese nombre.");
        }
        Proyecto p = new Proyecto();
        p.setUsuario(u);
        p.setNombreProyecto(nombreProyecto);
        p.setDescripcion(descripcion);
        return proyectoRepositorio.save(p);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Proyecto> listarPorUsuario(Integer idUsuario, Pageable pageable) {
        return proyectoRepositorio.findByUsuario_IdUsuario(idUsuario, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Proyecto obtenerDeUsuario(Integer idProyecto, Integer idUsuario) {
        return proyectoRepositorio.findByIdProyectoAndUsuario_IdUsuario(idProyecto, idUsuario)
                .orElseThrow(() -> new RecursoNoEncontradoException("Proyecto no encontrado o no pertenece al usuario"));
    }

    @Override
    public Proyecto actualizar(Integer idProyecto, Integer idUsuario, String nuevoNombre, String nuevaDescripcion) {
        Proyecto p = obtenerDeUsuario(idProyecto, idUsuario);
        if (nuevoNombre != null && !nuevoNombre.isBlank()
                && !p.getNombreProyecto().equalsIgnoreCase(nuevoNombre)) {
            if (proyectoRepositorio.existsByUsuario_IdUsuarioAndNombreProyectoIgnoreCase(idUsuario, nuevoNombre)) {
                throw new OperacionInvalidaException("Ya existe otro proyecto con ese nombre.");
            }
            p.setNombreProyecto(nuevoNombre);
        }
        if (nuevaDescripcion != null) {
            p.setDescripcion(nuevaDescripcion);
        }
        return proyectoRepositorio.save(p);
    }

    @Override
    public void eliminar(Integer idProyecto, Integer idUsuario) {
        Proyecto p = obtenerDeUsuario(idProyecto, idUsuario);
        proyectoRepositorio.delete(p);
    }
}
