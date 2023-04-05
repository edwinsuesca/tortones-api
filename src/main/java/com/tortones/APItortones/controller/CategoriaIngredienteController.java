package com.tortones.APItortones.controller;

import com.tortones.APItortones.model.CategoriaIngrediente;
import com.tortones.APItortones.repository.CategoriaIngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@ResponseBody
public class CategoriaIngredienteController {
    @Autowired
    private CategoriaIngredienteRepository categoriaIngredienteRepository;

    @GetMapping("/categorias-ingredientes")
    public List<CategoriaIngrediente> getAllCategoriaIngredientes() {
        return categoriaIngredienteRepository.findAll();
    }

    @PostMapping(value = "/categorias-ingredientes", consumes = "application/json")
    public CategoriaIngrediente createCategoriaIngrediente(@RequestBody CategoriaIngrediente categoriaIngrediente) {
        return categoriaIngredienteRepository.save(categoriaIngrediente);
    }

    @PutMapping("/categorias-ingredientes/{id}")
    public CategoriaIngrediente updateCategoriaIngrediente(@PathVariable Long id, @RequestBody CategoriaIngrediente categoriaIngrediente) {
        CategoriaIngrediente categoriaIngredienteExistente = categoriaIngredienteRepository.findById(id).orElse(null);
        if (categoriaIngredienteExistente != null) {
            if (categoriaIngrediente.getNombre() != null) {
                categoriaIngredienteExistente.setNombre(categoriaIngrediente.getNombre());
            }

            return categoriaIngredienteRepository.save(categoriaIngredienteExistente);
        }
        return null;
    }

    @DeleteMapping("/categorias-ingredientes/{id}")
    public Map<String, String> deleteCategoriaIngrediente(@PathVariable Long id) {
        CategoriaIngrediente categoriaIngredienteExistente = categoriaIngredienteRepository.findById(id).orElse(null);
        if(categoriaIngredienteExistente == null){
            return Map.of("message", "Categoria de ingrediente no existe");
        } else{
            categoriaIngredienteRepository.deleteById(id);
            return Map.of("message", "Categoria de ingrediente " + "con ID " + id + " eliminada exitosamente.");
        }
    }
}
