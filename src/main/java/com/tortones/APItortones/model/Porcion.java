package com.tortones.APItortones.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "porciones")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"fecha_creacion", "fecha_actualizacion"}, allowGetters = true)
public class Porcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "peso", nullable = false, unique = true)
    private String peso;

    @Column(name = "porcion", nullable = false, unique = true)
    private String porcion;

    @Column(name = "factor_precio", nullable = false)
    private Float factorPrecio;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date fechaCreacion;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date fechaActualizacion;

    @OneToMany(mappedBy = "porcion", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonManagedReference(value = "porciones-producto-compra")
    private List<ProductoCompra> productoCompra;

    public Porcion(Long id, String peso, String porcion, Float factorPrecio, List<ProductoCompra> productoCompra, Date fechaCreacion, Date fechaActualizacion) {
        this.id = id;
        this.peso = peso;
        this.porcion = porcion;
        this.factorPrecio = factorPrecio;
        this.productoCompra = productoCompra;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Porcion(Long id) {
        this.id = id;
    }

    public Porcion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getPorcion() {
        return porcion;
    }

    public void setPorcion(String porcion) {
        this.porcion = porcion;
    }

    public Float getFactorPrecio() {
        return factorPrecio;
    }

    public void setFactorPrecio(Float factorPrecio) {
        this.factorPrecio = factorPrecio;
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
