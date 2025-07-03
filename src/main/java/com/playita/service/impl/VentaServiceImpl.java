package com.playita.service.impl;

import com.playita.dto.DetalleVentaDTO;
import com.playita.dto.VentaDTO;
import com.playita.entity.*;
import com.playita.repository.VentaRepository;
import com.playita.service.ClienteService;
import com.playita.service.ProductoService;
import com.playita.service.VentaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private HttpSession session;

    @Override
    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Optional<Venta> obtenerVentaPorId(Integer id) {
        return ventaRepository.findById(id);
    }

    @Override
    public Venta guardarVenta(Venta venta) {
        venta.setFecha_v(LocalDate.now());
        venta.setHora_v(LocalTime.now());

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            venta.setUsuario(usuario);
        }

        if (venta.getDetalles() != null) {
            for (DetalleVenta detalle : venta.getDetalles()) {
                detalle.setVenta(venta);
            }
        }

        return ventaRepository.save(venta);
    }

    @Override
    public void eliminarVenta(Integer id) {
        ventaRepository.deleteById(id);
    }

    @Override
    public void guardarVentaDesdeDTO(VentaDTO ventaDTO) {
        Venta venta = new Venta();

        // 1. Fecha, hora y usuario
        venta.setFecha_v(LocalDate.now());
        venta.setHora_v(LocalTime.now());

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            throw new IllegalStateException("Usuario no autenticado");
        }
        venta.setUsuario(usuario);

        // 2. Cliente
        Cliente cliente = clienteService.buscarPorId(ventaDTO.getFk_id_clientes());
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente inválido");
        }
        venta.setCliente(cliente);

        // 3. Total
        venta.setTotal(ventaDTO.getTotal());

        // 4. Procesar detalles y stock
        List<DetalleVenta> detalles = new ArrayList<>();
        if (ventaDTO.getProductos() != null) {
            for (DetalleVentaDTO d : ventaDTO.getProductos()) {
                Producto producto = productoService.buscarPorId(d.getIdProducto());
                if (producto == null) {
                    throw new IllegalArgumentException("Producto inválido");
                }

                // ✅ Verificar stock disponible
                if (producto.getCantidadStock() < d.getCantidad()) {
                    throw new IllegalStateException("Stock insuficiente para el producto: " + producto.getNombre());
                }

                // ✅ Restar la cantidad vendida al stock actual
                producto.setCantidadStock(producto.getCantidadStock() - d.getCantidad());
                productoService.actualizar(producto); // Este método guarda el nuevo stock

                // Crear detalle
                DetalleVenta detalle = new DetalleVenta();
                detalle.setProducto(producto);
                detalle.setCantidad_ventaP(d.getCantidad());
                detalle.setSubtotal(d.getSubtotal());
                detalle.setVenta(venta);

                detalles.add(detalle);
            }

        }

        if (detalles.isEmpty()) {
            throw new IllegalArgumentException("Debe agregar al menos un producto a la venta.");
        }

        venta.setDetalles(detalles);

        // 5. Guardar la venta
        ventaRepository.save(venta);
    }

}