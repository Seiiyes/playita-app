package com.playita.service.impl;

import com.playita.entity.Usuario;
import com.playita.repository.UsuarioRepository;
import com.playita.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario guardar(Usuario usuario) {
        // Encriptar la contraseña si no está codificada
        if (usuario.getContrasena() != null && !usuario.getContrasena().startsWith("$2a$")) {
            String passwordCodificada = passwordEncoder.encode(usuario.getContrasena());
            usuario.setContrasena(passwordCodificada);
        }

        // Asignar campos automáticos
        usuario.setFechaCreacion(LocalDateTime.now());
        usuario.setEstado(1); // Estado activo por defecto

        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario autenticarUsuario(String correo, String contrasena) {
        Usuario usuario = usuarioRepository.findByCorreo(correo).orElse(null);
        if (usuario != null && passwordEncoder.matches(contrasena, usuario.getContrasena())) {
            usuario.setUltimoLogin(LocalDateTime.now());
            return usuarioRepository.save(usuario); // Guardamos el último login actualizado
        }
        return null;
    }

    @Override
    public boolean existsByCorreo(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

    @Override
    public boolean existsByDocumento(String documento) {
        // Si deseas validar documento, debes tener este método en el repositorio
        // return usuarioRepository.existsByDocumento(documento);
        return false; // Placeholder si aún no está implementado
    }
}
