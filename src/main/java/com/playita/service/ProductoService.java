package com.playita.service;

import com.playita.entity.Producto;
import java.util.List;

public interface ProductoService {
    Producto guardar(Producto producto);
    List<Producto> listar();
    Producto buscarPorId(Integer id);
    void eliminar(Integer id);
    void actualizar(Producto producto); // <- importante
}
