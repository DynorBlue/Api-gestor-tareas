package com.DynorBlue.ApiGestorTareas.GestorTareas.modelo;

import jakarta.persistence.*;
import org.w3c.dom.Text;

import java.util.Date;

public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComentario;

    @ManyToOne
    @JoinColumn(name = "idTarea")
    private Tarea tarea;

    @Column(length = 255, nullable = false)
    private String comentario;

    @Column(nullable = false)
    private Date fechaComentario;

    //getters setters y constructor


    public Comentario() {
    }

    public Comentario(String comentario, Integer idComentario, Date fechaComentario, Tarea tarea) {
        this.comentario = comentario;
        this.idComentario = idComentario;
        this.fechaComentario = fechaComentario;
        this.tarea = tarea;
    }

    public Integer getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Integer idComentario) {
        this.idComentario = idComentario;
    }

    public Date getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(Date fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }
}
