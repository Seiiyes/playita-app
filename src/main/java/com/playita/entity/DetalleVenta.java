package com.playita.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_ventas")
@IdClass(DetalleVentaId.class)
public class DetalleVenta {

    @Id
    @ManyToOne
    @JoinColumn(name = "fk_producto")
    private Producto producto;

    @Id
    @ManyToOne
    @JoinColumn(name = "fk_ventas")
    private Venta venta;

    private Integer cantidad_ventaP;
    private Double subtotal;

    // Getters y Setters
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Integer getCantidad_ventaP() {
        return cantidad_ventaP;
    }

    public void setCantidad_ventaP(Integer cantidad_ventaP) {
        this.cantidad_ventaP = cantidad_ventaP;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
