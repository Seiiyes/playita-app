package com.playita.service.impl;

import com.playita.entity.DetallePedido;
import com.playita.entity.DetallePedidoId;
import com.playita.repository.DetallePedidoRepository;
import com.playita.service.DetallePedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Override
    public DetallePedido guardar(DetallePedido detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }

    @Override
    public List<DetallePedido> listarPorPedido(Integer pedidoId) {
        return detallePedidoRepository.findByPedido_Id(pedidoId);
    }

    @Override
    public void eliminarPorPedido(Integer pedidoId) {
        List<DetallePedido> detalles = listarPorPedido(pedidoId);
        detallePedidoRepository.deleteAll(detalles);
    }
}
