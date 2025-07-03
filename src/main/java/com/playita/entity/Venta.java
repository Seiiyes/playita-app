package com.playita.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "tbl_ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk_ventas;

    private LocalDate fecha_v;
    private LocalTime hora_v;
    private Double total;

    @ManyToOne
    @JoinColumn(name = "fk_id_clientes")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "fk_id_usuario")
    private Usuario usuario;

    // NUEVO: Relación con los detalles de la venta
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> detalles;

    // Getters y Setters

    public Integer getPk_ventas() {
        return pk_ventas;
    }

    public void setPk_ventas(Integer pk_ventas) {
        this.pk_ventas = pk_ventas;
    }

    public LocalDate getFecha_v() {
        return fecha_v;
    }

    public void setFecha_v(LocalDate fecha_v) {
        this.fecha_v = fecha_v;
    }

    public LocalTime getHora_v() {
        return hora_v;
    }

    public void setHora_v(LocalTime hora_v) {
        this.hora_v = hora_v;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }
}
