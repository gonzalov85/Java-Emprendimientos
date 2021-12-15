package com.acme.emprendimientos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Where(clause = "activo = true")
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer"})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Nombre no puedeestar vacío")
    private String nombre;
    @NotEmpty(message = "Apellido no puede estar vacío")
    private String apellido;
    @NotEmpty(message = "Email no puede estar vacío")
    @Column(unique = true)
    @Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private String email;
    @NotEmpty(message = "Password no puede estar vacío")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Boolean activo = true;
    @CreationTimestamp
    private LocalDateTime fechaDeCreacion;
    private LocalDateTime ultimaModificacion;
    @NotEmpty(message = "Ciudad no puede estar vacío")
    private String ciudad;
    @NotEmpty(message = "Provincia no puede estar vacío")
    private String provincia;
    @NotEmpty(message = "Pais no puede estar vacío")
    private String pais;
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private TipoUsuario tipo;
    @OneToMany(mappedBy = "creador", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Emprendimiento> emprendimientos = new ArrayList<Emprendimiento>();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Boolean isActivo() {
        return activo;
    }
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }
    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }
    public LocalDateTime getUltimaModificacion() {
        return ultimaModificacion;
    }
    public void setUltimaModificacion(LocalDateTime ultimaModificacion) {
        this.ultimaModificacion = ultimaModificacion;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getProvincia() {
        return provincia;
    }
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public TipoUsuario getTipo() {
        return tipo;
    }
    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }
    /*     @JsonIgnore */
    public List<Emprendimiento> getEmprendimientos() {
        return emprendimientos;
    }
    public void setEmprendimientos(List<Emprendimiento> emprendimientos) {
        this.emprendimientos = emprendimientos;
    }
    public void addEmprendimiento(Emprendimiento emprendimiento) {
        emprendimientos.add(emprendimiento);
        emprendimiento.setCreador(this);
    }
    public void removeEmprendimiento(Emprendimiento emprendimiento) {
        emprendimientos.remove(emprendimiento);
        emprendimiento.setCreador(null);
    }
    @Override
    public String toString() {
        return "Usuario [activo=" + activo + ", apellido=" + apellido + ", ciudad=" + ciudad + ", email=" + email
                + ", fechaDeCreacion=" + fechaDeCreacion + ", id=" + id + ", nombre=" + nombre + ", pais=" + pais
                + ", password=" + password + ", provincia=" + provincia + ", tipo=" + tipo + ", ultimaModificacion="
                + ultimaModificacion + "]";
    }
}