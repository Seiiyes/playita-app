package com.playita.service;

import com.playita.dto.VentaDTO;
import com.playita.entity.Venta;
import java.util.List;
import java.util.Optional;

public interface VentaService {
    List<Venta> listarVentas();
    Optional<Venta> obtenerVentaPorId(Integer id);
    Venta guardarVenta(Venta venta);
    void eliminarVenta(Integer id);
    void guardarVentaDesdeDTO(VentaDTO ventaDTO);

}
