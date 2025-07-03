package com.playita.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_roles")
    private Integer pkIdRoles;

    @Column(name = "desc_rol", nullable = false, length = 35)
    private String descRol;

    // Getters y Setters
    public Integer getPkIdRoles() {
        return pkIdRoles;
    }

    public void setPkIdRoles(Integer pkIdRoles) {
        this.pkIdRoles = pkIdRoles;
    }

    public String getDescRol() {
        return descRol;
    }

    public void setDescRol(String descRol) {
        this.descRol = descRol;
    }
}
