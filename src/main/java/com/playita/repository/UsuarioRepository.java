package com.playita.repository;

import com.playita.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreo(String correo);
    boolean existsByCorreo(String correo);
    boolean existsByDocumento(String documento); // <- NUEVO
    boolean existsByCorreoIgnoreCase(String correo);

}
