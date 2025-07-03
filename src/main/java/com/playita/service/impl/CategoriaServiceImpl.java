package com.playita.service.impl;

import com.playita.entity.Categoria;
import com.playita.repository.CategoriaRepository;
import com.playita.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria guardar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria buscarPorId(Integer id) {
        Optional<Categoria> optional = categoriaRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(Integer id) {
        return categoriaRepository.existsById(id);
    }
}
