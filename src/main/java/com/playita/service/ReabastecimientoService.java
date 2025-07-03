package com.playita.service;

import com.playita.entity.Reabastecimiento;
import java.util.List;

public interface ReabastecimientoService {
    Reabastecimiento guardar(Reabastecimiento reabastecimiento);
    List<Reabastecimiento> listar();
    Reabastecimiento buscarPorId(Integer id);
    void eliminar(Integer id);
}
