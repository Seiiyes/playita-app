package com.playita.controller;

import com.playita.entity.Usuario;
import com.playita.entity.Rol;
import com.playita.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    // Mostrar formulario de registro
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "auth/registro";
    }

    // Procesar registro
    @PostMapping("/registro")
    public String procesarRegistro(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes redirectAttrs) {
        try {
            // Asignar rol por defecto: vendedor (ID 2)
            Rol rolPorDefecto = new Rol();
            rolPorDefecto.setPkIdRoles(2);
            usuario.setRol(rolPorDefecto);

            usuarioService.guardar(usuario);
            redirectAttrs.addFlashAttribute("registroExitoso", true);
            return "redirect:/auth/registro"; // Puedes redirigir a /auth/login si prefieres
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("registroError", "No se pudo registrar. Intenta nuevamente.");
            return "redirect:/auth/registro";
        }
    }
}
