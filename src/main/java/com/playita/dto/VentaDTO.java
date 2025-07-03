package com.playita.dto;

import java.util.List;

public class VentaDTO {
    private Integer fk_id_clientes;
    private Double total;
    private List<DetalleVentaDTO> productos;

    // Getters y Setters
    public Integer getFk_id_clientes() {
        return fk_id_clientes;
    }

    public void setFk_id_clientes(Integer fk_id_clientes) {
        this.fk_id_clientes = fk_id_clientes;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<DetalleVentaDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<DetalleVentaDTO> productos) {
        this.productos = productos;
    }
}
