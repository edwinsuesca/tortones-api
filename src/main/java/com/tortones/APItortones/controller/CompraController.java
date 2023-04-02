package com.tortones.APItortones.controller;

import com.tortones.APItortones.model.EstadoCompra;
import com.tortones.APItortones.model.Compra;
import com.tortones.APItortones.model.ProductoCompra;
import com.tortones.APItortones.repository.EstadoCompraRepository;
import com.tortones.APItortones.repository.CompraRepository;
import com.tortones.APItortones.repository.ProductoCompraRepository;
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
            Compra compraGuardada = compraRepository.save(compra);

            // Asociar cada producto a la compra y guardarlo en la base de datos
            if(compra.getProductosCompra() != null) {
                for (ProductoCompra producto : compra.getProductosCompra()) {
                    producto.setCompra(compraGuardada);
                    productoCompraRepository.save(producto);
                }
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

