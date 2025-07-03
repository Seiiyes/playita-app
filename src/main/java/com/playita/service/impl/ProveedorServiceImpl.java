package com.playita.service.impl;

import com.playita.entity.Proveedor;
import com.playita.repository.ProveedorRepository;
import com.playita.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public Proveedor guardar(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public List<Proveedor> listar() {
        return proveedorRepository.findAll();
    }

    @Override
    public Proveedor buscarPorId(Integer id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        proveedorRepository.deleteById(id);
    }
}
