package com.playita.controller;

import com.playita.entity.Rol;
import com.playita.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    // Listar roles
    @GetMapping
    public String listarRoles(Model model) {
        model.addAttribute("roles", rolService.listar());
        return "roles/index";
    }

    // Guardar nuevo rol
    @PostMapping("/guardar")
    public String guardarRol(@ModelAttribute Rol rol) {
        rolService.guardar(rol);
        return "redirect:/roles";
    }

    // Actualizar rol existente
    @PostMapping("/actualizar")
    public String actualizarRol(@ModelAttribute Rol rol) {
        rolService.guardar(rol); // save también actualiza si el ID ya existe
        return "redirect:/roles";
    }

    // Eliminar rol por ID
    @PostMapping("/eliminar")
    public String eliminarRol(@RequestParam("id") Integer id) {
        rolService.eliminar(id);
        return "redirect:/roles";
    }
}
