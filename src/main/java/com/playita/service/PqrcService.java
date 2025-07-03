package com.playita.service;

import com.playita.entity.Pqrc;
import java.util.List;

public interface PqrcService {
    Pqrc guardar(Pqrc pqrc);
    List<Pqrc> listar();
    Pqrc buscarPorId(Integer id);
    void eliminar(Integer id);
}
