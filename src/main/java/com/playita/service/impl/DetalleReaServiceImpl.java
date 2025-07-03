package com.playita.service.impl;

import com.playita.entity.DetalleRea;
import com.playita.entity.Producto;
import com.playita.repository.DetalleReaRepository;
import com.playita.repository.ProductoRepository;
import com.playita.service.DetalleReaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleReaServiceImpl implements DetalleReaService {

    @Autowired
    private DetalleReaRepository detalleReaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public DetalleRea guardar(DetalleRea detalleRea) {
        // Obtener el producto
        Producto producto = detalleRea.getProducto();

        // Actualizar el stock
        if (producto != null && detalleRea.getCantidad() != null) {
            int cantidad = detalleRea.getCantidad();
            producto.setCantidadStock(producto.getCantidadStock() + cantidad);
            productoRepository.save(producto); // Guardar nuevo stock
        }

        // Guardar el detalle de reabastecimiento
        return detalleReaRepository.save(detalleRea);
    }

    @Override
    public List<DetalleRea> listarPorReabastecimiento(Integer idReabastecimiento) {
        return detalleReaRepository.findByReabastecimiento_Id(idReabastecimiento);
    }

    @Override
    public void eliminarPorReabastecimiento(Integer idReabastecimiento) {
        List<DetalleRea> detalles = detalleReaRepository.findByReabastecimiento_Id(idReabastecimiento);
        detalleReaRepository.deleteAll(detalles);
    }
}
