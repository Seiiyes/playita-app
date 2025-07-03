package com.playita.service.impl;

import com.playita.entity.Cliente;
import com.playita.repository.ClienteRepository;
import com.playita.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public Optional<Cliente> buscarPorTelefono(String telC) {
        return clienteRepository.findByTelC(telC);
    }

    @Override
    public Cliente buscarPorCorreo(String correo) {
        return clienteRepository.findByCorreoC(correo).orElse(null);
    }
    @Override
    public Cliente buscarPorDocumento(String documentoC) {
    return clienteRepository.findByDocumentoC(documentoC)
        .orElse(null); // o lanzar excepción si prefieres
}

}
