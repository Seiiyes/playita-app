package com.playita.controller;

import com.playita.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping
    public String redirigirPorRol(Authentication auth) {
        if (auth != null && auth.getAuthorities() != null) {
            for (GrantedAuthority authority : auth.getAuthorities()) {
                String rol = authority.getAuthority();
                if (rol.equals("ROLE_ADMIN")) {
                    return "redirect:/dashboard/admin";
                } else if (rol.equals("ROLE_VENDEDOR")) {
                    return "redirect:/dashboard/vendedor";
                }
            }
        }
        return "redirect:/auth/login"; // fallback
    }

@GetMapping("/admin")
public String vistaAdmin(Model model, Authentication authentication) {
    agregarNombreAlModelo(model, authentication);
    model.addAttribute("mostrarSidebar", false);
    return "dashboard/admin";
}

@GetMapping("/vendedor")
public String vistaVendedor(Model model, Authentication authentication) {
    agregarNombreAlModelo(model, authentication);
    model.addAttribute("mostrarSidebar", false);
    return "dashboard/vendedor";
}

// Método reutilizable
private void agregarNombreAlModelo(Model model, Authentication authentication) {
    if (authentication != null && authentication.isAuthenticated()) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails customUserDetails) {
            String nombreCompleto = customUserDetails.getUsuario().getPrimerNombre() + " " +
                                    customUserDetails.getUsuario().getPrimerApellido();
            model.addAttribute("nombreCompleto", nombreCompleto);
        }
    }
}

}
