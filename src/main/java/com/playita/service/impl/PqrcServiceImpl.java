package com.playita.service.impl;

import com.playita.entity.Pqrc;
import com.playita.repository.PqrcRepository;
import com.playita.service.PqrcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PqrcServiceImpl implements PqrcService {

    @Autowired
    private PqrcRepository pqrcRepository;

    @Override
    public Pqrc guardar(Pqrc pqrc) {
        return pqrcRepository.save(pqrc);
    }

    @Override
    public List<Pqrc> listar() {
        return pqrcRepository.findAll();
    }

    @Override
    public Pqrc buscarPorId(Integer id) {
        return pqrcRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        pqrcRepository.deleteById(id);
    }
}
