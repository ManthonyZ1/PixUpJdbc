package org.ramosmiguel.pixup.model;

public class Municipio extends Catalogo
{
    private String municipio;
    private Integer id_estado;

    public Municipio() {
    }

    public String getMunicipio() {
        return municipio;
    }
    public Integer getId_estado() {
        return id_estado;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    @Override
    public String toString() {
        return "Municipio{" +
                "municipio='" + municipio + '\'' +
                ", id_estado=" + id_estado +
                ", id=" + id +
                '}';
    }
}