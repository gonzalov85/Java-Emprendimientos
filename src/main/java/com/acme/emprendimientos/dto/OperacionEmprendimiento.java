package com.acme.emprendimientos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OperacionEmprendimiento {

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

    @NotNull
    @Positive
    @JsonProperty(value = "id_usuario")
    private Long idUsuario;

    private List<Long> tags;

    private List<Long> capturas;

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

    public LocalDate getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDate fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
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

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Long> getTags() {
        return tags;
    }

    public void setTags(List<Long> tags) {
        this.tags = tags;
    }

}
