package com.playita.service;

import com.playita.entity.Categoria;
import java.util.List;

public interface CategoriaService {
    List<Categoria> listar();
    Categoria guardar(Categoria nombre);
    Categoria buscarPorId(Integer id);
    void eliminar(Integer id);
    boolean existePorId(Integer id);
}
