package com.playita.dto;

public class PqrcRequest {
    private String tipo;
    private String descripcion;
    private String documentoC;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDocumentoC() {
        return documentoC;
    }

    public void setDocumentoC(String documentoC) {
        this.documentoC = documentoC;
    }
}
