package com.playita.controller;

import com.playita.entity.Producto;
import com.playita.service.CategoriaService;
import com.playita.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    // GET: Mostrar todos los productos
    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.listar());
        model.addAttribute("categorias", categoriaService.listar());
        return "productos/index";
    }

    // POST: Guardar un nuevo producto
    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.guardar(producto);
        return "redirect:/productos";
    }

    // POST: Actualizar un producto existente
    @PostMapping("/actualizar")
    public String actualizarProducto(@ModelAttribute Producto producto) {
        productoService.guardar(producto);
        return "redirect:/productos";
    }

    // POST: Eliminar un producto
    @PostMapping("/eliminar")
    public String eliminarProducto(@RequestParam("id") Integer id) {
        productoService.eliminar(id);
        return "redirect:/productos";
    }
}
