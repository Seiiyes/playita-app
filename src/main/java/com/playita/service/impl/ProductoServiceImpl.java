package com.playita.service.impl;

import com.playita.entity.Producto;
import com.playita.repository.ProductoRepository;
import com.playita.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    @Override
    public Producto buscarPorId(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        productoRepository.deleteById(id);
    }
    @Override
    public void actualizar(Producto producto) {
        productoRepository.save(producto);
    }

}
