package com.playita.controller;

import com.playita.entity.Categoria;
import com.playita.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // Listar todas las categorías
    @GetMapping
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.listar());
        return "categorias/index";
    }

    // Guardar nueva categoría
    @PostMapping("/guardar")
    public String guardarCategoria(@ModelAttribute Categoria categoria) {
        categoriaService.guardar(categoria);
        return "redirect:/categorias?success";
    }

    // Actualizar categoría existente
    @PostMapping("/actualizar")
    public String actualizarCategoria(@ModelAttribute Categoria categoria) {
        categoriaService.guardar(categoria);
        return "redirect:/categorias?updated";
    }

    // Eliminar una categoría por su ID
    @PostMapping("/eliminar")
    public String eliminarCategoria(@RequestParam("id") Integer id) {
        categoriaService.eliminar(id);
        return "redirect:/categorias?deleted";
    }
}
