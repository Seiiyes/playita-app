package com.playita.controller;

import com.playita.dto.VentaDTO;
import com.playita.service.ClienteService;
import com.playita.service.ProductoService;
import com.playita.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProductoService productoService;

    // Mostrar lista de ventas
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("ventas", ventaService.listarVentas());
        model.addAttribute("clientes", clienteService.listar());
        model.addAttribute("productos", productoService.listar());
        return "ventas/index";
    }

    // Guardar venta usando DTO (mejor práctica)
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute VentaDTO ventaDTO) {
        ventaService.guardarVentaDesdeDTO(ventaDTO);
        return "redirect:/ventas";
    }

    // Eliminar venta
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        ventaService.eliminarVenta(id);
        return "redirect:/ventas";
    }
}
