package com.playita.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_usuario")
    private Integer id;

    @Column(name = "documento_u", nullable = false, unique = true)
    private String documento;

    @Column(name = "p_nombre_u", nullable = false)
    private String primerNombre;

    @Column(name = "s_nombre_u")
    private String segundoNombre;

    @Column(name = "p_apellido_u", nullable = false)
    private String primerApellido;

    @Column(name = "s_apellido_u")
    private String segundoApellido;

    @Column(name = "correo_u", nullable = false, unique = true)
    private String correo;

    @Column(name = "telefono_u")
    private String telefono;

    @Column(nullable = false)
    private String contrasena;

    @Column(name = "estado_usuario")
    private Integer estado;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "ultimo_login")
    private LocalDateTime ultimoLogin;

    @ManyToOne
    @JoinColumn(name = "fk_id_roles")
    private Rol rol;

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getPrimerNombre() { return primerNombre; }
    public void setPrimerNombre(String primerNombre) { this.primerNombre = primerNombre; }

    public String getSegundoNombre() { return segundoNombre; }
    public void setSegundoNombre(String segundoNombre) { this.segundoNombre = segundoNombre; }

    public String getPrimerApellido() { return primerApellido; }
    public void setPrimerApellido(String primerApellido) { this.primerApellido = primerApellido; }

    public String getSegundoApellido() { return segundoApellido; }
    public void setSegundoApellido(String segundoApellido) { this.segundoApellido = segundoApellido; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public Integer getEstado() { return estado; }
    public void setEstado(Integer estado) { this.estado = estado; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public LocalDateTime getUltimoLogin() { return ultimoLogin; }
    public void setUltimoLogin(LocalDateTime ultimoLogin) { this.ultimoLogin = ultimoLogin; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }
    @Transient
public String getNombreCompleto() {
    return String.format("%s %s %s %s",
        primerNombre != null ? primerNombre : "",
        segundoNombre != null ? segundoNombre : "",
        primerApellido != null ? primerApellido : "",
        segundoApellido != null ? segundoApellido : "").trim();
}

}
