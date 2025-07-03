package com.playita.entity;

import java.io.Serializable;
import java.util.Objects;

public class DetalleVentaId implements Serializable {

    private Integer producto;
    private Integer venta;

    public DetalleVentaId() {}

    public DetalleVentaId(Integer producto, Integer venta) {
        this.producto = producto;
        this.venta = venta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DetalleVentaId)) return false;
        DetalleVentaId that = (DetalleVentaId) o;
        return Objects.equals(producto, that.producto) &&
               Objects.equals(venta, that.venta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producto, venta);
    }
}
