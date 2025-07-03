package com.playita.util;

import com.playita.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalUserInfo {

    @ModelAttribute("nombreCompleto")
    public String nombreCompleto(Authentication auth) {
        if (auth != null && auth.getPrincipal() instanceof CustomUserDetails userDetails) {
            return userDetails.getNombreCompleto();
        }
        return "";
    }
}
