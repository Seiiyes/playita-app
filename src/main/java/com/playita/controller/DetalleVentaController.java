package com.playita.controller;

import com.playita.entity.DetalleVenta;
import com.playita.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas/{id}/detalle")
@CrossOrigin(origins = "*")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @PostMapping
    public ResponseEntity<DetalleVenta> guardar(@PathVariable Integer id,
                                                @RequestBody DetalleVenta detalle) {
        // El JSON debe tener los IDs de producto y venta, más cantidad y subtotal
        return ResponseEntity.ok(detalleVentaService.guardar(detalle));
    }
}
