package com.playita.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_pqrc")
public class Pqrc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk_id_pqrc;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPqrc tipo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    private LocalDate fecha;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "fk_id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "fk_id_usuario")
    private Usuario usuario; //

    // Getters y Setters

    public Integer getPk_id_pqrc() {
        return pk_id_pqrc;
    }

    public void setPk_id_pqrc(Integer pk_id_pqrc) {
        this.pk_id_pqrc = pk_id_pqrc;
    }

    public TipoPqrc getTipo() {
        return tipo;
    }

    public void setTipo(TipoPqrc tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
    
}
