package com.tortones.APItortones.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.*;

@Entity
@Table(name = "estadosCompra")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"fecha_creacion", "fecha_actualizacion"}, allowGetters = true)
public class EstadoCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date fechaCreacion;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date fechaActualizacion;

    @OneToMany(mappedBy = "estadoCompra", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonManagedReference(value = "estado_compra")
    private List<Compra> compra;

    public EstadoCompra(Long id, String nombre, List<Compra> compra) {
        this.id = id;
        this.nombre = nombre;
        this.compra = compra;
    }

    public EstadoCompra(Long id) {
        this.id = id;
    }

    public EstadoCompra(String nombre) {
        this.nombre = nombre;
    }

    public EstadoCompra() {
    }

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

    public List<Compra> getOrdenCompra() {
        return compra;
    }

    public void setOrdenCompra(List<Compra> compra) {
        this.compra = compra;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}
