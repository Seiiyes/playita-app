package com.playita.controller;

import com.playita.entity.*;
import com.playita.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private DetallePedidoService detallePedidoService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private UsuarioService usuarioService;

    // Mostrar todos los pedidos
    @GetMapping
    public String listarPedidos(Model model) {
        model.addAttribute("pedidos", pedidoService.listar());
        model.addAttribute("clientes", clienteService.listar());
        model.addAttribute("usuarios", usuarioService.listar());
        model.addAttribute("productos", productoService.listar());
        return "pedidos/index"; // ← Coincide con la vista HTML
    }

    // Guardar un nuevo pedido con sus detalles
    @PostMapping("/guardar")
    public String guardarPedido(@ModelAttribute Pedido pedido,
                                 @RequestParam("productoId") List<Integer> productoIds,
                                 @RequestParam("cantidad") List<Integer> cantidades,
                                 @RequestParam("precioUnitario") List<Double> preciosUnitarios) {

        pedido.setFechaPedido(LocalDate.now());

        // Guardar el pedido principal
        Pedido nuevoPedido = pedidoService.guardar(pedido);

        // Guardar los detalles del pedido
        for (int i = 0; i < productoIds.size(); i++) {
            Producto producto = productoService.buscarPorId(productoIds.get(i));
            int cantidad = cantidades.get(i);

            if (producto.getCantidadStock() >= cantidad) {
                // Descontar stock
                producto.setCantidadStock(producto.getCantidadStock() - cantidad);
                productoService.guardar(producto);

                // Crear detalle
                DetallePedido detalle = new DetallePedido();
                detalle.setPedido(nuevoPedido);
                detalle.setProducto(producto);
                detalle.setCantidad(cantidad);
                detalle.setPrecioUnitario(preciosUnitarios.get(i));

                detallePedidoService.guardar(detalle);
            } else {
                System.out.println("⚠️ Stock insuficiente para producto ID: " + producto.getId());
            }
        }

        return "redirect:/pedidos";
    }

    // Ver detalles del pedido (para el modal)
    @GetMapping("/detalle/{id}")
    public String verDetalle(@PathVariable Integer id, Model model) {
        Pedido pedido = pedidoService.buscarPorId(id);
        List<DetallePedido> detalles = detallePedidoService.listarPorPedido(id);

        model.addAttribute("pedido", pedido);
        model.addAttribute("detalles", detalles);
        return "pedidos/detalle :: tablaDetalle";
    }

    // Eliminar pedido y sus detalles
    @GetMapping("/eliminar/{id}")
    public String eliminarPedido(@PathVariable Integer id) {
        detallePedidoService.eliminarPorPedido(id);
        pedidoService.eliminar(id);
        return "redirect:/pedidos";
    }
}
