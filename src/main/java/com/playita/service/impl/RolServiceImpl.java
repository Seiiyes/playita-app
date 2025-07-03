package com.playita.service.impl;

import com.playita.entity.Rol;
import com.playita.repository.RolRepository;
import com.playita.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<Rol> listar() {
        return rolRepository.findAll();
    }

    @Override
    public Rol buscarPorId(Integer pkIdRoles) {
        Optional<Rol> optionalRol = rolRepository.findById(pkIdRoles);
        return optionalRol.orElse(null); // o lanzar excepción si prefieres
    }
    @Override
    public Rol guardar(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public void eliminar(Integer pkIdRoles) {
        rolRepository.deleteById(pkIdRoles);
    }

    @Override
    public boolean existePorId(Integer pkIdRoles) {
        return rolRepository.existsById(pkIdRoles);
    }

}
