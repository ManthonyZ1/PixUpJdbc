package org.ramosmiguel.pixup.model;

public class GeneroMusical extends Catalogo
{
    private String descripcion;

    public GeneroMusical() {
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "GeneroMusical{" +
                "descripcion='" + descripcion + '\'' +
                ", id=" + id +
                '}';
    }
}