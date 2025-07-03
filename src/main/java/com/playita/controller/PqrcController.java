package com.playita.controller;

import com.playita.dto.PqrcRequest;
import com.playita.entity.Pqrc;
import com.playita.entity.TipoPqrc;
import com.playita.entity.Cliente;
import com.playita.service.PqrcService;
import com.playita.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pqrc")
@CrossOrigin(origins = "*")
public class PqrcController {

    @Autowired
    private PqrcService pqrcService;

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody PqrcRequest pqrcRequest) {
        try {
            // Crear entidad y mapear campos
            Pqrc pqrc = new Pqrc();

            // Convertir String a Enum TipoPqrc
            TipoPqrc tipoEnum = TipoPqrc.valueOf(pqrcRequest.getTipo());
            pqrc.setTipo(tipoEnum);

            pqrc.setDescripcion(pqrcRequest.getDescripcion());

            // Buscar cliente por documento
            Cliente cliente = clienteService.buscarPorDocumento(pqrcRequest.getDocumentoC());
            if (cliente == null) {
                return ResponseEntity.badRequest().body("Cliente no encontrado para documento: " + pqrcRequest.getDocumentoC());
            }
            pqrc.setCliente(cliente);

            // Opcional: establecer fecha actual y estado
            pqrc.setFecha(LocalDate.now());
            pqrc.setEstado("Pendiente");

            Pqrc guardado = pqrcService.guardar(pqrc);
            return ResponseEntity.ok(guardado);

        } catch (IllegalArgumentException e) {
            // Ocurre si el valor tipo no coincide con el enum
            return ResponseEntity.badRequest().body("Tipo PQRC inválido: " + pqrcRequest.getTipo());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno al guardar PQRC");
        }
    }

    @GetMapping
    public ResponseEntity<List<Pqrc>> listar() {
        return ResponseEntity.ok(pqrcService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pqrc> buscar(@PathVariable Integer id) {
        Pqrc pqrc = pqrcService.buscarPorId(id);
        return pqrc != null ? ResponseEntity.ok(pqrc) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        pqrcService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
