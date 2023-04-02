package com.tortones.APItortones.controller;

import com.tortones.APItortones.model.Porcion;
import com.tortones.APItortones.model.ProductoCompra;
import com.tortones.APItortones.model.Usuario;
import com.tortones.APItortones.repository.PorcionRepository;
import com.tortones.APItortones.repository.ProductoCompraRepository;
import com.tortones.APItortones.repository.ProductoCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@ResponseBody
public class ProductoCompraController {

    @Autowired
    private ProductoCompraRepository productoCompraRepository;
    @Autowired
    private PorcionRepository porcionRepository;

    @GetMapping("/productos-compra")
    public List<ProductoCompra> getAllOrdenesCompra() {
        return productoCompraRepository.findAll();
    }

    @PostMapping(value = "/productos-compra", consumes = "application/json")
    public ProductoCompra createProductoCompra(@RequestBody ProductoCompra productoCompra) {
        return productoCompraRepository.save(productoCompra);
    }

    @GetMapping("/productos-compra/{id}")
    public ProductoCompra getProductoCompraById(@PathVariable Long id) {
        return productoCompraRepository.findById(id).orElse(null);
    }

    @PutMapping("/productos-compra/{id}")
    public ProductoCompra updateProductoCompra(@PathVariable Long id, @RequestBody ProductoCompra productoCompra) {
        ProductoCompra productoCompraExistente = productoCompraRepository.findById(id).orElse(null);
        if ((productoCompraExistente != null)) {
            if (productoCompra.getCantidad() != null) {
                productoCompraExistente.setCantidad(productoCompra.getCantidad());
            }

            if (productoCompra.getPorcion().getId() != null) {
                Long idPorcion = productoCompra.getPorcion().getId();
                Porcion porcionExistente = porcionRepository.findById(idPorcion).orElse(null);
                if(porcionExistente != null){
                    productoCompraExistente.setPorcion(porcionExistente);
                }
            }

            return productoCompraRepository.save(productoCompraExistente);
        }
        return null;
    }

    @DeleteMapping("/productos-compra/{id}")
    public String deleteProductoCompra(@PathVariable Long id) {
        ProductoCompra productoCompraExistente = productoCompraRepository.findById(id).orElse(null);
        if(productoCompraExistente == null){
            return "Producto de compra no existe";
        } else{
            productoCompraRepository.deleteById(id);
            return "Producto " + "con ID " + id + " eliminado exitosamente de la orden de Compra.";
        }
    }
}
