package com.playita.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            String rol = auth.getAuthority();

            if ("ROLE_ADMIN".equals(rol)) {
                response.sendRedirect("/dashboard/admin");
                return;
            } else if ("ROLE_VENDEDOR".equals(rol)) {
                response.sendRedirect("/dashboard/vendedor");
                return;
            }
        }

        // Si no se encuentra ningún rol conocido
        response.sendRedirect("/auth/login?error=rol");
    }
}
