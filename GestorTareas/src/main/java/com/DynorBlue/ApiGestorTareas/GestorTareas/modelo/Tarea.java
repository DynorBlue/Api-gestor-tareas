package com.DynorBlue.ApiGestorTareas.GestorTareas.modelo;

import jakarta.persistence.*;
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

    @Column(columnDefinition = "VARCHAR(100) default '5'")
    private String prioridad; // 5 importante, 1 no tan importante

    @Column(nullable = false)
    private Date fechaLimite;

    @Column(nullable = false)
    private Date fechaCreacion;

    // constructor setters y getters


    public Tarea() {
    }

    public Tarea(String descripcion, String prioridad, Proyecto proyecto, String titulo, Integer idTarea, Date fechaLimite, Date fechaCreacion, String estado) {
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.proyecto = proyecto;
        this.titulo = titulo;
        this.idTarea = idTarea;
        this.fechaLimite = fechaLimite;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
