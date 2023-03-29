package com.tortones.APItortones.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.*;

@Entity
@Table(name = "ordenesCompra")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"fecha_creacion", "fecha_actualizacion"}, allowGetters = true)
public class OrdenCompra {
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

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "estado_compra_id")
    private EstadoCompra estadoCompra;

    @JsonManagedReference
    @OneToMany(mappedBy = "ordenCompra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductoCompra> productoCompra;

    public OrdenCompra(Long id, String nombre, Usuario usuario, EstadoCompra estadoCompra, List<ProductoCompra> productoCompra) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.estadoCompra = estadoCompra;
        this.productoCompra = productoCompra;
    }

    public OrdenCompra(Long id) {
        this.id = id;
    }

    public OrdenCompra() {
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public EstadoCompra getEstadoCompra() {
        return estadoCompra;
    }

    public void setEstadoCompra(EstadoCompra estadoCompra) {
        this.estadoCompra = estadoCompra;
    }

    public List<ProductoCompra> getProductoCompra() {
        return productoCompra;
    }

    public void setProductoCompra(List<ProductoCompra> productoCompra) {
        this.productoCompra = productoCompra;
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
