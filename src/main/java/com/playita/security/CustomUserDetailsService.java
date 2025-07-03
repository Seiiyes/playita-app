package com.playita.security;

import com.playita.entity.Usuario;
import com.playita.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String correo_u) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(correo_u)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuario no encontrado con correo: " + correo_u));

        return new CustomUserDetails(usuario); // devuelves tu implementación personalizada
    }
}
