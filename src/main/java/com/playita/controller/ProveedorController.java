package com.playita.controller;

import com.playita.entity.Proveedor;
import com.playita.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    // Listar todos los proveedores
    @GetMapping
    public String listarProveedores(Model model) {
        model.addAttribute("proveedores", proveedorService.listar());
        return "proveedores/index"; // Vista en templates/proveedores/index.html
    }

    // Guardar nuevo proveedor
    @PostMapping("/guardar")
    public String guardarProveedor(@ModelAttribute Proveedor proveedor) {
        proveedorService.guardar(proveedor);
        return "redirect:/proveedores";
    }

    // Actualizar proveedor existente
    @PostMapping("/actualizar")
    public String actualizarProveedor(@ModelAttribute Proveedor proveedor) {
        proveedorService.guardar(proveedor); // save actualiza si ya tiene ID
        return "redirect:/proveedores";
    }

    // Eliminar proveedor por ID
    @PostMapping("/eliminar")
    public String eliminarProveedor(@RequestParam("id") Integer id) {
        proveedorService.eliminar(id);
        return "redirect:/proveedores";
    }
}
