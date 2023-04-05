package com.tortones.APItortones.controller;

import com.tortones.APItortones.model.*;
import com.tortones.APItortones.model.Ingrediente;
import com.tortones.APItortones.repository.CategoriaIngredienteRepository;
import com.tortones.APItortones.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@ResponseBody
public class IngredienteController {
    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Autowired
    private CategoriaIngredienteRepository categoriaIngredienteRepository;

    @GetMapping("/ingredientes")
    public List<Ingrediente> getAllIngredientes() {
        return ingredienteRepository.findAll();
    }

    @PostMapping(value = "/ingredientes", consumes = "application/json")
    public Ingrediente createIngrediente(@RequestBody Ingrediente ingrediente) {
        return ingredienteRepository.save(ingrediente);
    }

    @PutMapping("/ingredientes/{id}")
    public Ingrediente updateIngrediente(@PathVariable Long id, @RequestBody Ingrediente ingrediente) {
        Ingrediente ingredienteExistente = ingredienteRepository.findById(id).orElse(null);
        if (ingredienteExistente != null) {
            if (ingrediente.getNombre() != null) {
                ingredienteExistente.setNombre(ingrediente.getNombre());
            }
            if (ingrediente.getDescripcion() != null) {
                ingredienteExistente.setDescripcion(ingrediente.getDescripcion());
            }
            if (ingrediente.getColor() != null) {
                ingredienteExistente.setColor(ingrediente.getColor());
            }

            if (ingrediente.getCategoriaIngrediente().getId() != null) {
                Long idCategoriaIngrediente = ingrediente.getCategoriaIngrediente().getId();
                CategoriaIngrediente categoriaExistente = categoriaIngredienteRepository.findById(idCategoriaIngrediente).orElse(null);
                if(categoriaExistente != null){
                    ingredienteExistente.setCategoriaIngrediente(categoriaExistente);
                }
            }
            return ingredienteRepository.save(ingredienteExistente);
        }
        return null;
    }

    @DeleteMapping("/ingredientes/{id}")
    public Map<String, String> deleteIngrediente(@PathVariable Long id) {
        Ingrediente ingredienteExistente = ingredienteRepository.findById(id).orElse(null);
        if(ingredienteExistente == null){
            return Map.of("message", "Ingrediente no existe");
        } else{
            ingredienteRepository.deleteById(id);
            return Map.of("message", "Ingrediente " + "con ID " + id + " eliminado exitosamente.");
        }
    }
}
