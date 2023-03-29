package com.tortones.APItortones.repository;

import com.tortones.APItortones.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
