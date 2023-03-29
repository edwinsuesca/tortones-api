package com.tortones.APItortones.controller;

import com.tortones.APItortones.model.Producto;
import com.tortones.APItortones.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@ResponseBody
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/productos")
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @PostMapping(value = "/productos", consumes = "application/json")
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @GetMapping("/productos/{id}")
    public Producto getProductoById(@PathVariable Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @PutMapping("/productos/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Producto productoExistente = productoRepository.findById(id).orElse(null);
        if (productoExistente != null) {
            if (producto.getUrlImagen() != null) {
                productoExistente.setUrlImagen(producto.getUrlImagen());
            }
            if (producto.getNombre() != null) {
                productoExistente.setNombre(producto.getNombre());
            }
            if (producto.getDescripcion() != null) {
                productoExistente.setDescripcion(producto.getDescripcion());
            }
            if (producto.getPrecio() != null) {
                productoExistente.setPrecio(producto.getPrecio());
            }

            return productoRepository.save(productoExistente);
        }
        return null;
    }

    @DeleteMapping("/productos/{id}")
    public String deleteProducto(@PathVariable Long id) {
        Producto productoExistente = productoRepository.findById(id).orElse(null);
        if(productoExistente == null){
            return "Producto no existe";
        } else{
            productoRepository.deleteById(id);
            return "Producto " + "con ID " + id + " eliminado exitosamente.";
        }
    }
}
