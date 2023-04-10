package com.tortones.APItortones.repository;

import com.tortones.APItortones.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Usuario findByCorreo(String correo);
    List<Usuario> findByRol(String rol);
}