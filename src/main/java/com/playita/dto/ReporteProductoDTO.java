package com.playita.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReporteProductoDTO {
    private String nombreProducto;
    private String categoria;
    private Long cantidadVendida;
    private Double totalGenerado;
    private Integer stockActual;
    private LocalDate fechaUltimaVenta;

    public ReporteProductoDTO(String nombreProducto, String categoria,
                              Long cantidadVendida, Double totalGenerado,
                              Integer stockActual, LocalDate fechaUltimaVenta) {
        this.nombreProducto = nombreProducto;
        this.categoria = categoria;
        this.cantidadVendida = cantidadVendida;
        this.totalGenerado = totalGenerado;
        this.stockActual = stockActual;
        this.fechaUltimaVenta = fechaUltimaVenta;
    }

    // Getters y setters
    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Long getCantidadVendida() { return cantidadVendida; }
    public void setCantidadVendida(Long cantidadVendida) { this.cantidadVendida = cantidadVendida; }

    public Double getTotalGenerado() { return totalGenerado; }
    public void setTotalGenerado(Double totalGenerado) { this.totalGenerado = totalGenerado; }

    public Integer getStockActual() { return stockActual; }
    public void setStockActual(Integer stockActual) { this.stockActual = stockActual; }

    public LocalDate getFechaUltimaVenta() { return fechaUltimaVenta; }
    public void setFechaUltimaVenta(LocalDate fechaUltimaVenta) { this.fechaUltimaVenta = fechaUltimaVenta; }

    // ✅ NUEVO: Para evitar errores en Thymeleaf
    public String getFechaUltimaVentaFormato() {
        return fechaUltimaVenta != null
                ? fechaUltimaVenta.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                : "";
    }
}
