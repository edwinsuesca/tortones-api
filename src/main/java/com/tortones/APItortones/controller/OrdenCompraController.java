package com.tortones.APItortones.controller;

import com.tortones.APItortones.model.EstadoCompra;
import com.tortones.APItortones.model.OrdenCompra;
import com.tortones.APItortones.repository.EstadoCompraRepository;
import com.tortones.APItortones.repository.OrdenCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@ResponseBody
public class OrdenCompraController {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    @Autowired
    private EstadoCompraRepository estadoCompraRepository;

    @GetMapping("/ordenes-compra")
    public List<OrdenCompra> getAllOrdenesCompra() {
        return ordenCompraRepository.findAll();
    }

    @PostMapping(value = "/ordenes-compra", consumes = "application/json")
    public OrdenCompra createOrdenCompra(@RequestBody OrdenCompra ordenCompra) {
        EstadoCompra estadoEnProceso = estadoCompraRepository.findByNombre("En proceso");
        ordenCompra.setEstadoCompra(estadoEnProceso);
        return ordenCompraRepository.save(ordenCompra);
    }

    @GetMapping("/ordenes-compra/{id}")
    public OrdenCompra getOrdenCompraById(@PathVariable Long id) {
        return ordenCompraRepository.findById(id).orElse(null);
    }

    @PutMapping("/ordenes-compra/{id}")
    public OrdenCompra updateOrdenCompra(@PathVariable Long id, @RequestBody OrdenCompra ordenCompra) {
        OrdenCompra ordenCompraExistente = ordenCompraRepository.findById(id).orElse(null);
        if ((ordenCompraExistente != null)) {
            if (ordenCompra.getEstadoCompra() != null) {
                ordenCompraExistente.setEstadoCompra(ordenCompra.getEstadoCompra());
            }

            return ordenCompraRepository.save(ordenCompraExistente);
        }
        return null;
    }

    @DeleteMapping("/ordenes-compra/{id}")
    public String deleteOrdenCompra(@PathVariable Long id) {
        OrdenCompra ordenCompraExistente = ordenCompraRepository.findById(id).orElse(null);
        if(ordenCompraExistente == null){
            return "Orden de compra no existe";
        } else{
            ordenCompraRepository.deleteById(id);
            return "Orden de compra " + "con ID " + id + " eliminada exitosamente.";
        }
    }
}

