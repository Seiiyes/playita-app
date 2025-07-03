package com.playita.service;

import com.playita.entity.Pedido;
import java.util.List;

public interface PedidoService {
    Pedido guardar(Pedido pedido);
    List<Pedido> listar();
    Pedido buscarPorId(Integer id);
    void eliminar(Integer id); 
}
