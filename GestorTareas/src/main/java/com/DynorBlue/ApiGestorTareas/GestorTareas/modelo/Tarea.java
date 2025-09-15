package com.DynorBlue.ApiGestorTareas.GestorTareas.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.w3c.dom.Text;

import java.util.Date;

@Entity
@Table(name = "Tarea")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTarea;

    @ManyToOne
    @JoinColumn(name = "idProyecto")
    private Proyecto proyecto;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false, length = 250)
    private String descripcion;

    @Column(columnDefinition = "VARCHAR(100) default 'Ocupado'")
    private String estado; // en proceso, concluida, pendiente

    @Column
    @Min(1)
    @Max(5)
    private Integer prioridad; // 5 importante, 1 no tan importante

    @Column(nullable = false)
    private Date fechaLimite;

    @Column(nullable = false)
    private Date fechaCreacion;

    // constructor setters y getters


    public Tarea() {
    }

    public Tarea(String descripcion, Integer idTarea, Date fechaLimite, Date fechaCreacion, String estado, Proyecto proyecto, Integer prioridad, String titulo) {
        this.descripcion = descripcion;
        this.idTarea = idTarea;
        this.fechaLimite = fechaLimite;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.proyecto = proyecto;
        this.prioridad = prioridad;
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
