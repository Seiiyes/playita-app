package com.playita.controller;

import com.playita.entity.Pqrc;
import com.playita.entity.Usuario;
import com.playita.entity.Cliente;
import com.playita.security.CustomUserDetails;
import com.playita.service.PqrcService;
import com.playita.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/pqrc")
public class PqrcViewController {

    @Autowired
    private PqrcService pqrcService;

    @Autowired
    private ClienteService clienteService;

    // Listar todos los PQRCs
    @GetMapping
    public String listar(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Usuario usuario = userDetails.getUsuario();
        Cliente cliente = clienteService.buscarPorCorreo(usuario.getCorreo());
        if (cliente != null) {
            model.addAttribute("documentoCliente", cliente.getDocumentoC());
        }
        List<Pqrc> lista = pqrcService.listar();
        model.addAttribute("listaPQRC", lista);
        return "pqrc/index";
    }

    // Guardar un nuevo PQRC
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Pqrc pqrc, @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (pqrc.getCliente() == null || pqrc.getCliente().getDocumentoC() == null) {
            return "redirect:/pqrc";
        }

        Cliente cliente = clienteService.buscarPorDocumento(pqrc.getCliente().getDocumentoC());
        if (cliente == null) {
            return "redirect:/pqrc";
        }

        pqrc.setCliente(cliente);
        pqrc.setUsuario(userDetails.getUsuario());
        pqrc.setEstado("Pendiente");
        pqrc.setFecha(LocalDate.now());

        pqrcService.guardar(pqrc);
        return "redirect:/pqrc";
    }

    // Procesar actualización desde el modal
    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Pqrc pqrcForm, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Pqrc existente = pqrcService.buscarPorId(pqrcForm.getPk_id_pqrc());
        if (existente == null) {
            return "redirect:/pqrc";
        }

        Cliente cliente = clienteService.buscarPorDocumento(pqrcForm.getCliente().getDocumentoC());
        if (cliente == null) {
            return "redirect:/pqrc";
        }

        existente.setTipo(pqrcForm.getTipo());
        existente.setDescripcion(pqrcForm.getDescripcion());
        existente.setEstado(pqrcForm.getEstado());
        existente.setFecha(LocalDate.now());
        existente.setCliente(cliente);
        existente.setUsuario(userDetails.getUsuario());

        pqrcService.guardar(existente);
        return "redirect:/pqrc";
    }

    // Eliminar PQRC
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        pqrcService.eliminar(id);
        return "redirect:/pqrc";
    }

}
