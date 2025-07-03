package com.playita.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_clientes")
    private Integer pkIdClientes;

    @Column(name = "documento_c", nullable = false, unique = true)
    private String documentoC;

    @Column(name = "p_nombre_c", nullable = false)
    private String pNombreC;

    @Column(name = "s_nombre_c")
    private String sNombreC;

    @Column(name = "p_apellido_c", nullable = false)
    private String pApellidoC;

    @Column(name = "s_apellido_c")
    private String sApellidoC;

    @Column(name = "correo_c", nullable = false)
    private String correoC;

    @Column(name = "tel_c", nullable = false)
    private String telC;

    // 👇 Getter adicional para usar c.id en Thymeleaf
    public Integer getId() {
        return pkIdClientes;
    }

    // Getters y Setters estándar
    public Integer getPkIdClientes() { return pkIdClientes; }
    public void setPkIdClientes(Integer pkIdClientes) { this.pkIdClientes = pkIdClientes; }

    public String getDocumentoC() { return documentoC; }
    public void setDocumentoC(String documentoC) { this.documentoC = documentoC; }

    public String getPNombreC() { return pNombreC; }
    public void setPNombreC(String pNombreC) { this.pNombreC = pNombreC; }

    public String getSNombreC() { return sNombreC; }
    public void setSNombreC(String sNombreC) { this.sNombreC = sNombreC; }

    public String getPApellidoC() { return pApellidoC; }
    public void setPApellidoC(String pApellidoC) { this.pApellidoC = pApellidoC; }

    public String getSApellidoC() { return sApellidoC; }
    public void setSApellidoC(String sApellidoC) { this.sApellidoC = sApellidoC; }

    public String getCorreoC() { return correoC; }
    public void setCorreoC(String correoC) { this.correoC = correoC; }

    public String getTelC() { return telC; }
    public void setTelC(String telC) { this.telC = telC; }
    @Transient
public String getNombreCompleto() {
    return String.format("%s %s %s %s",
        pNombreC != null ? pNombreC : "",
        sNombreC != null ? sNombreC : "",
        pApellidoC != null ? pApellidoC : "",
        sApellidoC != null ? sApellidoC : "").trim();
}

}
