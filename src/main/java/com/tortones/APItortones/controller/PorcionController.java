package com.tortones.APItortones.controller;

import com.tortones.APItortones.model.Porcion;
import com.tortones.APItortones.model.Porcion;
import com.tortones.APItortones.repository.PorcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS })
@ResponseBody
public class PorcionController {
    @Autowired
    private PorcionRepository porcionRepository;

    @GetMapping("/porciones")
    public List<Porcion> getAllPorciones() {
        return porcionRepository.findAll();
    }

    @PostMapping(value = "/porciones", consumes = "application/json")
    public Porcion createPorcion(@RequestBody Porcion porcion) {
        return porcionRepository.save(porcion);
    }

    @GetMapping("/porciones/{id}")
    public Porcion getPorcionById(@PathVariable Long id) {
        return porcionRepository.findById(id).orElse(null);
    }

    @PutMapping("/porciones/{id}")
    public Porcion updatePorcion(@PathVariable Long id, @RequestBody Porcion porcion) {
        Porcion porcionExistente = porcionRepository.findById(id).orElse(null);
        if (porcionExistente != null) {
            if (porcion.getPeso() != null) {
                porcionExistente.setPeso(porcion.getPeso());
            }
            if (porcion.getPorcion() != null) {
                porcionExistente.setPorcion(porcion.getPorcion());
            }
            if (porcion.getFactorPrecio() != null) {
                porcionExistente.setFactorPrecio(porcion.getFactorPrecio());
            }

            return porcionRepository.save(porcionExistente);
        }
        return null;
    }

    @DeleteMapping("/porciones/{id}")
    public String deletePorcion(@PathVariable Long id) {
        Porcion porcionExistente = porcionRepository.findById(id).orElse(null);
        if(porcionExistente == null){
            return "Porcion no existe";
        } else{
            porcionRepository.deleteById(id);
            return "Porcion " + "con ID " + id + " eliminada exitosamente.";
        }
    }
}
