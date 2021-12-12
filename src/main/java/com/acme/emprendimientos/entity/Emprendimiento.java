package com.acme.emprendimientos.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Emprendimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nombre no puede ser vacío")
    private String nombre;

    @NotEmpty(message = "Descripción no puede ser vacío")
    private String descripcion;

    @NotEmpty(message = "Contenido no puede ser vacío")
    private String contenido;

    @CreationTimestamp
    private LocalDate fechaDeCreacion;

    private BigDecimal objetivo;

    private boolean publicado;

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDate getfechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setfechaDeCreacion(LocalDate fechaCreacion) {
        this.fechaDeCreacion = fechaCreacion;
    }

    public BigDecimal getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(BigDecimal objetivo) {
        this.objetivo = objetivo;
    }

    public boolean isPublicado() {
        return publicado;
    }

    public void setPublicado(boolean publicado) {
        this.publicado = publicado;
    }

}
