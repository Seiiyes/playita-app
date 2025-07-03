package com.playita.service.impl;

import com.playita.entity.DetalleVenta;
import com.playita.repository.DetalleVentaRepository;
import com.playita.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Override
    public DetalleVenta guardar(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }
}
