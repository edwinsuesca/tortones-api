package com.tortones.APItortones.controller;

import com.tortones.APItortones.model.Usuario;
import com.tortones.APItortones.repository.UsuarioRepository;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@ResponseBody
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @PostMapping(value = "/usuarios", consumes = "application/json")
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @GetMapping("/usuarios/{id}")
    public Usuario getUsuarioById(@PathVariable Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @PutMapping("/usuarios/{id}")
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);
        if (usuarioExistente != null) {
            if (usuario.getRol() != null) {
                usuarioExistente.setRol(usuario.getRol());
            }
            if (usuario.getNombre() != null) {
                usuarioExistente.setNombre(usuario.getNombre());
            }
            if (usuario.getApellido() != null) {
                usuarioExistente.setApellido(usuario.getApellido());
            }
            if (usuario.getDireccion() != null) {
                usuarioExistente.setDireccion(usuario.getDireccion());
            }
            if (usuario.getCorreo() != null) {
                usuarioExistente.setCorreo(usuario.getCorreo());
            }
            if (usuario.getTelefono() != null) {
                usuarioExistente.setTelefono(usuario.getTelefono());
            }
            if (usuario.getClave() != null) {
                usuarioExistente.setClave(usuario.getClave());
            }
            return usuarioRepository.save(usuarioExistente);
        }
        return null;
    }

    @DeleteMapping("/usuarios/{id}")
    public String deleteUsuario(@PathVariable Long id) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);
        if(usuarioExistente == null){
            return "Usuario no existe";
        } else{
            usuarioRepository.deleteById(id);
            return "Usuario " + "con ID " + id + " eliminado exitosamente.";
        }
    }
}
