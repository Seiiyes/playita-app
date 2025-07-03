package com.playita.service.impl;

import com.playita.entity.Reabastecimiento;
import com.playita.repository.ReabastecimientoRepository;
import com.playita.service.ReabastecimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReabastecimientoServiceImpl implements ReabastecimientoService {

    @Autowired
    private ReabastecimientoRepository repository;

    @Override
    public Reabastecimiento guardar(Reabastecimiento reabastecimiento) {
        return repository.save(reabastecimiento);
    }

    @Override
    public List<Reabastecimiento> listar() {
        return repository.findAll();
    }

    @Override
    public Reabastecimiento buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
