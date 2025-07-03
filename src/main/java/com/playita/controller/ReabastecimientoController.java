package com.playita.controller;

import com.playita.entity.DetalleRea;
import com.playita.entity.Producto;
import com.playita.entity.Reabastecimiento;
import com.playita.service.DetalleReaService;
import com.playita.service.ProductoService;
import com.playita.service.ProveedorService;
import com.playita.service.ReabastecimientoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Controller
@RequestMapping("/reabastecimientos")
public class ReabastecimientoController {

    @Autowired
    private ReabastecimientoService reabastecimientoService;

    @Autowired
    private DetalleReaService detalleReaService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProveedorService proveedorService;

    // Página principal del módulo con tabla y modales
    @GetMapping
    public String index(Model model) {
        model.addAttribute("reabastecimientos", reabastecimientoService.listar());
        model.addAttribute("productos", productoService.listar());
        model.addAttribute("proveedores", proveedorService.listar());
        return "reabastecimientos/index";
    }

    // Guardar nuevo reabastecimiento
    @PostMapping("/guardar")
    public String guardar(
            @ModelAttribute Reabastecimiento reabastecimiento,
            @RequestParam("productoId") List<Integer> productoIds,
            @RequestParam("cantidad") List<Integer> cantidades
    ) {
        reabastecimiento.setFecha(LocalDate.now());
        reabastecimiento.setHora(LocalTime.now());

        Reabastecimiento nuevo = reabastecimientoService.guardar(reabastecimiento);

        for (int i = 0; i < productoIds.size(); i++) {
            Producto producto = productoService.buscarPorId(productoIds.get(i));
            DetalleRea detalle = new DetalleRea();
            detalle.setProducto(producto);
            detalle.setReabastecimiento(nuevo);
            detalle.setCantidad(cantidades.get(i));
            detalleReaService.guardar(detalle);
        }

        return "redirect:/reabastecimientos";
    }

    // Eliminar reabastecimiento por ID
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        detalleReaService.eliminarPorReabastecimiento(id);
        reabastecimientoService.eliminar(id);
        return "redirect:/reabastecimientos";
    }

    // Devolver detalles de un reabastecimiento en formato JSON (para JS)
    @GetMapping("/detalle/{id}")
    @ResponseBody
    public ResponseEntity<List<Map<String, Object>>> obtenerDetalles(@PathVariable Integer id) {
        List<DetalleRea> detalles = detalleReaService.listarPorReabastecimiento(id);
        List<Map<String, Object>> respuesta = new ArrayList<>();

        for (DetalleRea d : detalles) {
            Map<String, Object> item = new HashMap<>();
            item.put("producto", d.getProducto().getNombre());
            item.put("cantidad", d.getCantidad());
            respuesta.add(item);
        }

        return ResponseEntity.ok(respuesta);
    }
}
