package com.playita.service;

import com.playita.entity.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Cliente guardar(Cliente cliente);
    List<Cliente> listar();
    Cliente buscarPorId(Integer id);
    void eliminar(Integer id);
    Optional<Cliente> buscarPorTelefono(String telC);
    Cliente buscarPorCorreo(String correoC);
    Cliente buscarPorDocumento(String documentoC);
}
