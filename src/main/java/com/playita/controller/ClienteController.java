package com.playita.controller;

import com.playita.entity.Cliente;
import com.playita.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> guardar(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.guardar(cliente));
    }

    @PostMapping("/crear-rapido")
public ResponseEntity<?> crearDesdeVenta(
        @RequestParam("p_nombre_c") String pNombreC,
        @RequestParam("p_apellido_c") String pApellidoC,
        @RequestParam("tel_c") String telC,
        @RequestParam("documento_c") String documentoC,
        @RequestParam("correo_c") String correoC
) {
    if (telC == null || telC.trim().isEmpty()) {
        return ResponseEntity.badRequest().body("El teléfono es obligatorio");
    }

    Optional<Cliente> existente = clienteService.buscarPorTelefono(telC);
    if (existente.isPresent()) {
        return ResponseEntity.badRequest().body("Ya existe un cliente con ese teléfono");
    }

    Cliente cliente = new Cliente();
    cliente.setPNombreC(pNombreC);
    cliente.setPApellidoC(pApellidoC);
    cliente.setTelC(telC);
    cliente.setDocumentoC(documentoC);
    cliente.setCorreoC(correoC);

    Cliente guardado = clienteService.guardar(cliente);
    return ResponseEntity.ok(guardado);
}


    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok(clienteService.listar());
    }

    @GetMapping("/documento/{documento}")
    public ResponseEntity<Cliente> obtenerClientePorDocumento(@PathVariable String documento) {
        Cliente cliente = clienteService.buscarPorDocumento(documento);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Integer id) {
        Cliente cliente = clienteService.buscarPorId(id);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
