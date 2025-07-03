package com.playita.controller;

import com.playita.entity.DetalleRea;
import com.playita.service.DetalleReaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reabastecimientos/{reabastecimientoId}/detalle")
@CrossOrigin(origins = "*")
public class DetalleReaController {

    @Autowired
    private DetalleReaService detalleReaService;

    @PostMapping
    public ResponseEntity<DetalleRea> guardarDetalle(@PathVariable Integer reabastecimientoId,
                                                     @RequestBody DetalleRea detalle) {
        // El objeto ya debe contener el producto y el ID del reabastecimiento asociado
        return ResponseEntity.ok(detalleReaService.guardar(detalle));
    }
}
