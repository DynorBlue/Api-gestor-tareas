package com.DynorBlue.ApiGestorTareas.GestorTareas.repositorio;



import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Proyecto;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Tarea;
import com.DynorBlue.ApiGestorTareas.GestorTareas.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProyectoRepositorio extends JpaRepository<Proyecto, Integer> {
    List<Tarea>findAllOrderByUsuarioAsc(Usuario usuario);
}
