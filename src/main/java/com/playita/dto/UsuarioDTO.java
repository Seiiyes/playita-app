package com.playita.dto;

import com.playita.entity.Usuario;

public class UsuarioDTO {
    private String documento;
    private String nombreCompleto;
    private String correo;
    private String telefono;
    private String rol;

    public UsuarioDTO(Usuario usuario) {
        this.documento = usuario.getDocumento();
        this.nombreCompleto = usuario.getPrimerNombre() + " " + usuario.getPrimerApellido();
        this.correo = usuario.getCorreo();
        this.telefono = usuario.getTelefono();
        this.rol = usuario.getRol() != null ? usuario.getRol().getDescRol() : "Sin rol";
    }

    // Getters y setters
    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
