package com.playita.controller;

import com.playita.entity.Usuario;
import com.playita.entity.Rol;
import com.playita.service.UsuarioService;
import com.playita.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/usuarios")
public class UsuarioVistaController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // MOSTRAR TODOS LOS USUARIOS
    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listar());
        model.addAttribute("roles", rolService.listar());
        return "usuarios/usuarios";
    }

    // GUARDAR NUEVO USUARIO
    @PostMapping("/guardar")
    public String guardarUsuario(
            @RequestParam("fkIdRoles") Integer pkIdRoles,
            @ModelAttribute Usuario usuario,
            RedirectAttributes redirectAttrs) {

        Rol rol = rolService.buscarPorId(pkIdRoles);
        usuario.setRol(rol);
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena())); // encriptar contraseña
        usuario.setFechaCreacion(LocalDateTime.now());
        usuarioService.guardar(usuario);
        redirectAttrs.addFlashAttribute("mensaje", "✅ Usuario creado correctamente.");
        return "redirect:/usuarios";
    }

    // ACTUALIZAR USUARIO
    @PostMapping("/actualizar")
    public String actualizarUsuario(
            @RequestParam("fkIdRoles") Integer pkIdRoles,
            @ModelAttribute Usuario usuario,
            RedirectAttributes redirectAttrs) {

        Usuario existente = usuarioService.buscarPorId(usuario.getId());
        if (existente != null) {
            existente.setDocumento(usuario.getDocumento());
            existente.setPrimerNombre(usuario.getPrimerNombre());
            existente.setSegundoNombre(usuario.getSegundoNombre());
            existente.setPrimerApellido(usuario.getPrimerApellido());
            existente.setSegundoApellido(usuario.getSegundoApellido());
            existente.setCorreo(usuario.getCorreo());
            existente.setEstado(usuario.getEstado());
            existente.setRol(rolService.buscarPorId(pkIdRoles));
            existente.setUltimoLogin(LocalDateTime.now());

            usuarioService.guardar(existente);
            redirectAttrs.addFlashAttribute("mensaje", "✅ Usuario actualizado correctamente.");
        } else {
            redirectAttrs.addFlashAttribute("error", "❌ Usuario no encontrado.");
        }

        return "redirect:/usuarios";
    }

    // ELIMINAR USUARIO (POST)
    @PostMapping("/eliminar")
    public String eliminarUsuario(@RequestParam("id") Integer id, RedirectAttributes redirectAttrs) {
        usuarioService.eliminar(id);
        redirectAttrs.addFlashAttribute("mensaje", "🗑️ Usuario eliminado correctamente.");
        return "redirect:/usuarios";
    }

    // OBTENER USUARIO PARA EDICIÓN (AJAX opcional)
    @GetMapping("/editar/{id}")
    @ResponseBody
    public Usuario obtenerUsuario(@PathVariable Integer id) {
        return usuarioService.buscarPorId(id);
    }
}
