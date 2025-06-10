package org.ramosmiguel.pixup.model;

public class Colonia extends Catalogo
{
    private String nombre;
    private Integer cp;
    private Integer municipio_id;

    public Colonia() {}

    public String getNombre() {
        return nombre;
    }
    public Integer getCp() {
        return cp;
    }
    public Integer getMunicipio_id() {
        return municipio_id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setCp(Integer cp) {
        this.cp = cp;
    }
    public void setMunicipio_id(Integer municipio_id) {
        this.municipio_id = municipio_id;
    }

    @Override
    public String toString() {
        return "Colonia{" +
                "nombre='" + nombre + '\'' +
                ", cp='" + cp + '\'' +
                ", municipio_id=" + municipio_id +
                ", id=" + id +
                '}';
    }
}