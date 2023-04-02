package com.tortones.APItortones.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.*;

@Entity
@Table(name = "productosCompra")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"fecha_creacion", "fecha_actualizacion"}, allowGetters = true)
public class ProductoCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date fechaCreacion;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date fechaActualizacion;

    @ManyToOne
    @JoinColumn(name = "compra_id")
    @JsonBackReference(value = "orden-producto")
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    @JsonBackReference(value = "productoCompra-producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "porcion_id")
    @JsonBackReference(value = "porciones-producto-compra")
    private Porcion porcion;

    public ProductoCompra(Long id, Integer cantidad, Date fechaCreacion, Date fechaActualizacion, Compra compra, Producto producto, Porcion porcion) {
        this.id = id;
        this.cantidad = cantidad;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.compra = compra;
        this.producto = producto;
        this.porcion = porcion;
    }

    public ProductoCompra() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
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

    public Porcion getPorcion() {
        return porcion;
    }

    public void setPorcion(Porcion porcion) {
        this.porcion = porcion;
    }
}
