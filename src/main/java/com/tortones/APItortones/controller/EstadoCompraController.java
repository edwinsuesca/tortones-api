package com.tortones.APItortones.controller;

import com.tortones.APItortones.model.EstadoCompra;
import com.tortones.APItortones.repository.EstadoCompraRepository;
import com.tortones.APItortones.repository.EstadoCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@ResponseBody
public class EstadoCompraController {

    @Autowired
    private EstadoCompraRepository estadoCompraRepository;

    @GetMapping("/estados-compra")
    public List<EstadoCompra> getAllEstadosCompra() {
        return estadoCompraRepository.findAll();
    }

    @PostMapping(value = "/estados-compra", consumes = "application/json")
    public EstadoCompra createEstadoCompra(@RequestBody EstadoCompra estadoCompra) {
        return estadoCompraRepository.save(estadoCompra);
    }

    @GetMapping("/estados-compra/{id}")
    public EstadoCompra getEstadoCompraById(@PathVariable Long id) {
        return estadoCompraRepository.findById(id).orElse(null);
    }

    @PutMapping("/estados-compra/{id}")
    public EstadoCompra updateEstadoCompra(@PathVariable Long id, @RequestBody EstadoCompra estadoCompra) {
        EstadoCompra estadoCompraExistente = estadoCompraRepository.findById(id).orElse(null);
        if ((estadoCompraExistente != null)) {
            if (estadoCompra.getNombre() != null) {
                estadoCompraExistente.setNombre(estadoCompra.getNombre());
            }

            return estadoCompraRepository.save(estadoCompraExistente);
        }
        return null;
    }

    @DeleteMapping("/estados-compra/{id}")
    public String deleteEstadoCompra(@PathVariable Long id) {
        EstadoCompra estadoCompraExistente = estadoCompraRepository.findById(id).orElse(null);
        if(estadoCompraExistente == null){
            return "Estado de compra no existe";
        } else{
            estadoCompraRepository.deleteById(id);
            return "Estado de compra " + "con ID " + id + " eliminada exitosamente.";
        }
    }
}
