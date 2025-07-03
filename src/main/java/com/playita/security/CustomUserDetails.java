package com.playita.security;

import com.playita.entity.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Implementación personalizada de UserDetails para adaptar la entidad Usuario a Spring Security.
 */
public class CustomUserDetails implements UserDetails {

    private final Usuario usuario;

    public CustomUserDetails(Usuario usuario) {
        this.usuario = usuario;
    }

    // Permite obtener el objeto Usuario completo (sin exponerlo a serialización JSON)
    public Usuario getUsuario() {
        return usuario;
    }

    public String getNombreCompleto() {
        return usuario.getPrimerNombre() + " " + usuario.getPrimerApellido();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Spring Security requiere roles con prefijo "ROLE_"
        String rol = "ROLE_" + usuario.getRol().getDescRol().toUpperCase();
        return Collections.singletonList(new SimpleGrantedAuthority(rol));
    }

    @Override
    public String getPassword() {
        return usuario.getContrasena();
    }

    @Override
    public String getUsername() {
        return usuario.getCorreo();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Puedes implementar lógica real si lo necesitas
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Puedes implementar lógica real si lo necesitas
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Puedes implementar lógica real si lo necesitas
    }

    @Override
    public boolean isEnabled() {
        return usuario.getEstado() != null && usuario.getEstado() == 1;
    }
}
