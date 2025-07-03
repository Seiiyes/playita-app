package com.playita.service.impl;

import com.playita.entity.Pedido;
import com.playita.repository.PedidoRepository;
import com.playita.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public Pedido guardar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido buscarPorId(Integer id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        pedidoRepository.deleteById(id); // ✅ Implementación faltante
    }
}
