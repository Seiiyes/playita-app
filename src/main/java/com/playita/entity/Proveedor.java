package com.playita.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_proveedores")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_proveedores")
    private Integer id;

    @Column(name = "p_nombre_p")
    private String pNombre;

    @Column(name = "s_nombre_p")
    private String sNombre;

    @Column(name = "p_apellido_p")
    private String pApellido;

    @Column(name = "s_apellido_p")
    private String sApellido;

    @Column(name = "telefono_p")
    private String telefono;

    @Column(name = "correo_p")
    private String correo;

    @Column(name = "direccion_p")
    private String direccion;

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPNombre() {
        return pNombre;
    }

    public void setPNombre(String pNombre) {
        this.pNombre = pNombre;
    }

    public String getSNombre() {
        return sNombre;
    }

    public void setSNombre(String sNombre) {
        this.sNombre = sNombre;
    }

    public String getPApellido() {
        return pApellido;
    }

    public void setPApellido(String pApellido) {
        this.pApellido = pApellido;
    }

    public String getSApellido() {
        return sApellido;
    }

    public void setSApellido(String sApellido) {
        this.sApellido = sApellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // Opcional: nombre completo para mostrar
    @Override
    public String toString() {
        return pNombre + " " + sNombre + " " + pApellido + " " + sApellido;
    }
}
