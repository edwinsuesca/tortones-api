package com.tortones.APItortones.repository;

import com.tortones.APItortones.model.EstadoCompra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoCompraRepository extends JpaRepository<EstadoCompra, Long> {
    EstadoCompra findByNombre(String nombre);
}
