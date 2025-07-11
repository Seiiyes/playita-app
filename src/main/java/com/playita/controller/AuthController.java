package com.playita.controller;

import com.playita.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/recuperar")
    @ResponseBody // ⬅️ Agrega esto para devolver texto en vez de redirigir
    public String recuperarContrasena(@RequestParam("correo") String correo) {
        correo = correo.trim().toLowerCase();

        if (!usuarioService.existsByCorreo(correo)) {
            return "ERROR"; // El JS lo interpretará y redirigirá
        }

        System.out.println("✅ Enlace enviado a: " + correo);
        return "OK"; // El JS lo interpretará y redirigirá
    }

}
