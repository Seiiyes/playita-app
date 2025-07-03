package com.playita.repository;

import com.playita.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByTelC(String telC);
    Optional<Cliente> findByCorreoC(String correoC);
    Optional<Cliente> findByDocumentoC(String documentoC);


}
