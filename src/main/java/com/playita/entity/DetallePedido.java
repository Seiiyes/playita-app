package com.playita.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_pedido")
@IdClass(DetallePedidoId.class)
public class DetallePedido {

    @Id
    @ManyToOne
    @JoinColumn(name = "fk_id_pedido", nullable = false)
    private Pedido pedido;

    @Id
    @ManyToOne
    @JoinColumn(name = "fk_id_producto", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;

    // Getters y Setters

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
