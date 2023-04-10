package com.tortones.APItortones.controller;

import com.tortones.APItortones.model.*;
import com.tortones.APItortones.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@ResponseBody
public class CompraController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private EstadoCompraRepository estadoCompraRepository;

    @Autowired
    private ProductoCompraRepository productoCompraRepository;

    @Autowired
    private PorcionRepository porcionRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/compras")
    public List<Compra> getAllOrdenesCompra() {
        return compraRepository.findAll();
    }

    @PostMapping(value = "/compras", consumes = "application/json")
    @Transactional
    public ResponseEntity<Compra> createOrdenCompra(@RequestBody Compra compra) {
        EstadoCompra estadoEnProceso = estadoCompraRepository.findByNombre("En proceso");
        compra.setEstadoCompra(estadoEnProceso);

        try {
            // Guardar la compra en la base de datos
            compra.setTotal(0.0F);
            Compra compraGuardada = compraRepository.save(compra);

            // Asociar cada producto a la compra y calcular el subtotal
            if(compra.getProductosCompra() != null) {
                float total = 0.0F;
                for (ProductoCompra producto : compra.getProductosCompra()) {
                    Producto productoExistente = productoRepository.findById(producto.getProducto().getId()).orElse(null);
                    Porcion porcionExistente = porcionRepository.findById(producto.getPorcion().getId()).orElse(null);
                    if(productoExistente != null && porcionExistente != null){
                        Float factor = porcionExistente.getFactorPrecio();
                        Float precio = productoExistente.getPrecio();
                        Integer cantidad = producto.getCantidad();
                        float subtotal = factor * precio * cantidad;
                        total += subtotal;
                        producto.setSubtotal(subtotal);
                        producto.setCompra(compraGuardada);
                        productoCompraRepository.save(producto);
                    }
                }
                compraGuardada.setTotal(total);
                compraRepository.save(compraGuardada);
            }

            return new ResponseEntity(compraGuardada, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/compras/{id}")
    public Compra getOrdenCompraById(@PathVariable Long id) {
        return compraRepository.findById(id).orElse(null);
    }

    @PutMapping("/compras/{id}")
    public Compra updateOrdenCompra(@PathVariable Long id, @RequestBody Compra compra) {
        Compra compraExistente = compraRepository.findById(id).orElse(null);
        if ((compraExistente != null)) {
            if (compra.getEstadoCompra() != null) {
                compraExistente.setEstadoCompra(compra.getEstadoCompra());
            }

            return compraRepository.save(compraExistente);
        }
        return null;
    }

    @DeleteMapping("/compras/{id}")
    public String deleteOrdenCompra(@PathVariable Long id) {
        Compra compraExistente = compraRepository.findById(id).orElse(null);
        if(compraExistente == null){
            return "Orden de compra no existe";
        } else{
            compraRepository.deleteById(id);
            return "Orden de compra " + "con ID " + id + " eliminada exitosamente.";
        }
    }
}

