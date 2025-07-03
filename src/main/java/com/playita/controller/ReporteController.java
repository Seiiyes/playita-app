package com.playita.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.playita.dto.ReporteProductoDTO;
import com.playita.service.CategoriaService;
import com.playita.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private CategoriaService categoriaService;

    // ✅ Se registra el módulo JavaTime para soportar LocalDate
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("categorias", categoriaService.listar());
        model.addAttribute("productosJson", "[]");
        model.addAttribute("ventasJson", "[]");
        return "reportes";
    }

    @PostMapping("/filtrar")
    public String generarReporte(@RequestParam Map<String, String> filtros, Model model) {
        try {
            List<ReporteProductoDTO> resultados = reporteService.consultarPorFiltros(filtros);
            List<Map<String, Object>> ventasPorFecha = reporteService.obtenerVentasPorFecha(
                    LocalDate.parse(filtros.get("startDate")),
                    LocalDate.parse(filtros.get("endDate"))
            );

            // ✅ Serialización correcta con soporte a fechas Java 8
            String productosJson = objectMapper.writeValueAsString(resultados);
            String ventasJson = objectMapper.writeValueAsString(ventasPorFecha);

            model.addAttribute("resultados", resultados);
            model.addAttribute("ventasPorFecha", ventasPorFecha);
            model.addAttribute("productosJson", productosJson);
            model.addAttribute("ventasJson", ventasJson);

            model.addAttribute("startDate", filtros.get("startDate"));
            model.addAttribute("endDate", filtros.get("endDate"));
            model.addAttribute("categoriaSeleccionada", filtros.get("categoria"));
            model.addAttribute("categorias", categoriaService.listar());

        } catch (JsonProcessingException e) {
            model.addAttribute("error", "Error al generar el reporte");
            e.printStackTrace();
        }

        return "reportes";
    }
}
