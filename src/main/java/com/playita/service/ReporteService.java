package com.playita.service;

import com.playita.dto.ReporteProductoDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ReporteService {

    // Reporte detallado de productos
    List<ReporteProductoDTO> consultarPorFiltros(Map<String, String> filtros);

    // Ingresos totales por fecha (para gráfico)
    List<Map<String, Object>> obtenerVentasPorFecha(LocalDate inicio, LocalDate fin);
}
