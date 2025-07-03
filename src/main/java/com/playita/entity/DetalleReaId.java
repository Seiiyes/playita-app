package com.playita.entity;

import java.io.Serializable;
import java.util.Objects;

public class DetalleReaId implements Serializable {

    private Integer producto;
    private Integer reabastecimiento;

    public DetalleReaId() {}

    public DetalleReaId(Integer producto, Integer reabastecimiento) {
        this.producto = producto;
        this.reabastecimiento = reabastecimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DetalleReaId that)) return false;
        return Objects.equals(producto, that.producto) &&
               Objects.equals(reabastecimiento, that.reabastecimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producto, reabastecimiento);
    }
}
