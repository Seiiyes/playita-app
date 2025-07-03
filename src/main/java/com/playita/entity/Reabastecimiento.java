package com.playita.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tbl_reabastecimiento")
public class Reabastecimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_reabastecimiento")
    private Integer id;

    private LocalDate fecha;
    private LocalTime hora;
    private Double costo;

    @Column(name = "estado_compra")
    private String estadoCompra;

    @Column(name = "forma_pago")
    private String formaPago;

    @ManyToOne
    @JoinColumn(name = "fk_id_proveedores")
    private Proveedor proveedor;

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public String getEstadoCompra() {
        return estadoCompra;
    }

    public void setEstadoCompra(String estadoCompra) {
        this.estadoCompra = estadoCompra;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
