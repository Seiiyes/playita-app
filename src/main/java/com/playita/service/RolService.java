package com.playita.service;

import com.playita.entity.Rol;

import java.util.List;

public interface RolService {

    // Listar todos los roles
    List<Rol> listar();

    // Buscar un rol por su ID
    Rol buscarPorId(Integer pkIdRoles);

    // Guardar o actualizar un rol
    Rol guardar(Rol rol);

    // Eliminar un rol por su ID
    void eliminar(Integer pkIdRoles);

    // Verificar existencia por ID
    boolean existePorId(Integer pkIdRoles);
}
