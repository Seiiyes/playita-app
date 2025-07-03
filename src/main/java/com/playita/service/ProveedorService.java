package com.playita.service;

import com.playita.entity.Proveedor;
import java.util.List;

public interface ProveedorService {
    Proveedor guardar(Proveedor proveedor);
    List<Proveedor> listar();
    Proveedor buscarPorId(Integer id);
    void eliminar(Integer id);
}
