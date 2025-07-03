package com.playita.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        // Puedes pasar datos si el usuario está logueado
        // model.addAttribute("usuarioLogueado", "Juan");
        return "home"; // nombre del archivo HTML sin extensión
    }
}
