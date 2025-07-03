package com.playita.service;

import com.playita.entity.DetallePedido;
import java.util.List;

public interface DetallePedidoService {
    DetallePedido guardar(DetallePedido detallePedido);

    List<DetallePedido> listarPorPedido(Integer pedidoId);

    void eliminarPorPedido(Integer pedidoId);
}
