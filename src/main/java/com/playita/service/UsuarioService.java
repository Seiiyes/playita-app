package com.playita.service;

import com.playita.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario guardar(Usuario usuario);
    List<Usuario> listar();
    Usuario buscarPorId(Integer id);
    void eliminar(Integer id);
    Usuario autenticarUsuario(String correo, String contrasena);
    boolean existsByCorreo(String correo);
    boolean existsByDocumento(String documento); // <- NUEVO
    Usuario buscarPorCorreo(String correo);

    void actualizarContrasena(String correo, String nuevaContrasena);

}
