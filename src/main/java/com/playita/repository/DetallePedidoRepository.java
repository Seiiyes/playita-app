package com.playita.repository;

import com.playita.entity.DetallePedido;
import com.playita.entity.DetallePedidoId;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, DetallePedidoId> {
    List<DetallePedido> findByPedido_Id(Integer pedidoId);
}
