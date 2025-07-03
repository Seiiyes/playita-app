package com.playita.repository;

import com.playita.dto.ReporteProductoDTO;
import java.time.LocalDate;

import java.util.List;
import java.util.Map;

public interface ReporteRepository {
    List<ReporteProductoDTO> consultarPorFiltros(Map<String, String> filtros);
    List<Object[]> obtenerIngresosPorFecha(LocalDate inicio, LocalDate fin);
}