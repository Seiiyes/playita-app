package com.playita.controller;

import com.playita.entity.DetallePedido;
import com.playita.service.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos/{pedidoId}/detalle")
@CrossOrigin(origins = "*")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    @PostMapping
    public ResponseEntity<DetallePedido> guardar(@RequestBody DetallePedido detalle) {
        return ResponseEntity.ok(detallePedidoService.guardar(detalle));
    }
}
