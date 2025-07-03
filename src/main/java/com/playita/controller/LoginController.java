package com.playita.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/auth/login")
    public String mostrarFormularioLogin(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {

        if (error != null) {
            model.addAttribute("loginError", true);
        }

        if (logout != null) {
            model.addAttribute("logoutSuccess", true);
        }

        return "auth/login"; // Vista: src/main/resources/templates/auth/login.html
    }
}
