package com.DynorBlue.ApiGestorTareas.GestorTareas.modelo;

import jakarta.persistence.*;
import org.w3c.dom.Text;

import java.util.Date;

@Entity
@Table(name = "proyecto")
public class Proyecto {

    @Column(nullable = false, length = 200)
    private String nombreProyecto;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProyecto;

    @Column(length = 1000)
    private String descripcion;

    @Column(nullable = false)
    private Date fechaCreacion;

    //contructor, getters y setters


    public Proyecto(String descripcion, Usuario usario, String nombreProyecto, Integer idProyecto, Date fechaCreacion) {
        this.descripcion = descripcion;
        this.usuario = usario;
        this.nombreProyecto = nombreProyecto;
        this.idProyecto = idProyecto;
        this.fechaCreacion = fechaCreacion;
    }

    public Proyecto() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public Usuario getUsario() {
        return usuario;
    }

    public void setUsario(Usuario usario) {
        this.usuario = usario;
    }
}
