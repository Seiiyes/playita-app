package com.playita.service;

import com.playita.entity.DetalleRea;
import java.util.List;

public interface DetalleReaService {
    DetalleRea guardar(DetalleRea detalle);
    List<DetalleRea> listarPorReabastecimiento(Integer idReabastecimiento);
    void eliminarPorReabastecimiento(Integer idReabastecimiento);
}
