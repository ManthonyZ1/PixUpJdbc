package org.ramosmiguel.pixup.model;

public class TipoDomicilio extends Catalogo {
    private String descripcion;
    private Integer tblDomicilioId;

    public TipoDomicilio() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getTblDomicilioId() {
        return tblDomicilioId;
    }

    public void setTblDomicilioId(Integer tblDomicilioId) {
        this.tblDomicilioId = tblDomicilioId;
    }

    @Override
    public String toString() {
        return "TipoDomicilio{" +
                "descripcion='" + descripcion + '\'' +
                ", tblDomicilioId=" + tblDomicilioId +
                ", id=" + id +
                '}';
    }
}
