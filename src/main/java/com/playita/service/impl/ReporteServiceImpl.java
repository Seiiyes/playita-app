package com.playita.service.impl;

import com.playita.dto.ReporteProductoDTO;
import com.playita.repository.ReporteRepository;
import com.playita.service.ReporteService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    @Override
    public List<ReporteProductoDTO> consultarPorFiltros(Map<String, String> filtros) {
        return reporteRepository.consultarPorFiltros(filtros);
    }
    @Override
    public List<Map<String, Object>> obtenerVentasPorFecha(LocalDate inicio, LocalDate fin) {
        List<Object[]> resultados = reporteRepository.obtenerIngresosPorFecha(inicio, fin);
        List<Map<String, Object>> lista = new ArrayList<>();

        for (Object[] fila : resultados) {
            Map<String, Object> map = new HashMap<>();
            map.put("fecha", fila[0]);
            map.put("total", fila[1]);
            lista.add(map);
        }

        return lista;
    }

}