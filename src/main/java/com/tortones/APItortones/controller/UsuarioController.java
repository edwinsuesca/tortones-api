package com.tortones.APItortones.controller;

import com.tortones.APItortones.model.SesionUsuario;
import com.tortones.APItortones.model.Usuario;
import com.tortones.APItortones.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@ResponseBody
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/usuarios")
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @PostMapping(value = "/usuarios", consumes = "application/json")
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        String claveEncriptada = bCryptPasswordEncoder.encode(usuario.getClave());
        usuario.setClave(claveEncriptada);
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
                String claveEncriptada = bCryptPasswordEncoder.encode(usuario.getClave());
                usuarioExistente.setClave(claveEncriptada);
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

    @PostMapping("/login")
    public ResponseEntity<SesionUsuario> login(@RequestBody Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findByCorreo(usuario.getCorreo());
        if (usuarioExistente != null) {
            if (bCryptPasswordEncoder.matches(usuario.getClave(), usuarioExistente.getClave())) {
                //String token = generarTokenDeAutenticacion(usuario);
                Long id = usuarioExistente.getId();
                String rol = usuarioExistente.getRol();
                String nombre = usuarioExistente.getNombre();
                String apellido = usuarioExistente.getApellido();
                String telefono = usuarioExistente.getTelefono();
                String correo = usuarioExistente.getCorreo();
                SesionUsuario sesion = new SesionUsuario(id, rol, nombre, apellido, telefono, correo);

                return new ResponseEntity<>(sesion, HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
}
