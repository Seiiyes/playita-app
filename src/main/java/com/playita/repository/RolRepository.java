package com.playita.repository;

import com.playita.entity.Rol;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Integer> {
     Optional<Rol> findByDescRol(String descRol);
}
