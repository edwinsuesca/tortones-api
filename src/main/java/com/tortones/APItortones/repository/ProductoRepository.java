package com.tortones.APItortones.repository;

import com.tortones.APItortones.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
