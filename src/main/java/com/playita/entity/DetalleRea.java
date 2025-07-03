package com.playita.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_rea")
@IdClass(DetalleReaId.class)
public class DetalleRea {

    @Id
    @ManyToOne
    @JoinColumn(name = "fk_id_producto")
    private Producto producto;

    @Id
    @ManyToOne
    @JoinColumn(name = "fk_id_reabastecimiento")
    private Reabastecimiento reabastecimiento;

    @Column(name = "cantidad_reabastecimiento")
    private Integer cantidad;

    // Getters y Setters

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Reabastecimiento getReabastecimiento() {
        return reabastecimiento;
    }

    public void setReabastecimiento(Reabastecimiento reabastecimiento) {
        this.reabastecimiento = reabastecimiento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
