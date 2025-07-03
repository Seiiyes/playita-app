package com.playita.controller;

import com.playita.dto.UsuarioDTO;
import com.playita.entity.Usuario;
import com.playita.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> guardar(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.guardar(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuarioLogin) {
        Usuario usuario = usuarioService.autenticarUsuario(usuarioLogin.getCorreo(), usuarioLogin.getContrasena());
        if (usuario == null) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
        return ResponseEntity.ok(new UsuarioDTO(usuario));
    }

    @GetMapping
    public List<UsuarioDTO> obtenerTodosLosUsuarios() {
        return usuarioService.listar()
                .stream()
                .map(UsuarioDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Integer id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @GetMapping("/validar-correo")
    public ResponseEntity<Boolean> validarCorreo(@RequestParam String correo) {
        boolean existe = usuarioService.existsByCorreo(correo);
        return ResponseEntity.ok(!existe); // true si está disponible
    }

    @GetMapping("/validar-documento")
    public ResponseEntity<Boolean> validarDocumento(@RequestParam String documento) {
        boolean existe = usuarioService.existsByDocumento(documento);
        return ResponseEntity.ok(!existe); // true si está disponible
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
