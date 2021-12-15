package com.acme.emprendimientos.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @NotNull
    private String url;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario owner;

    private Long emprendimientoId;
    private Long usuarioId;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "empredimiento_id",
            joinColumns = @JoinColumn(name = "emprendimiento_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "emprendimientoId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voto> votos = new ArrayList<Voto>();
    private Integer contadorDeVotos = 0;
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Evento> eventos;

    public void addEvento(Evento evento) {
        if (this.eventos == null) {
            this.eventos = new ArrayList<>();
        }
        this.eventos.add(evento);
    }

    public Integer getContadorDeVotos() {
        return contadorDeVotos;
    }

    public void setContadorDeVotos(Integer contadorDeVotos) {
        this.contadorDeVotos = contadorDeVotos;
    }




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

    public LocalDate getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDate fechaCreacion) {
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

    public Usuario getOwner() {
        return owner;
    }

    public void setOwner(Usuario owner) {
        this.owner = owner;
    }

    public void agregarTag(Tag tag) {
        tags.add(tag);
        tag.getEmprendimientos().add(this);
    }

    public void removerTag(Tag tag) {
        tags.remove(tag);
        tag.getEmprendimientos().remove(null);
    }

    public List<Tag> getTags() {
        return tags;
    }


}
