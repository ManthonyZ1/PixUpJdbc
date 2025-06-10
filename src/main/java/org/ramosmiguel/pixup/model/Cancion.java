package org.ramosmiguel.pixup.model;

public class Cancion extends Catalogo{

    private String titulo;
    private String duracion;
    private Integer disco_id;

    public Cancion() {
    }

    public String getTitulo() {
        return titulo;
    }
    public String getDuracion() {
        return duracion;
    }
    public Integer getDisco_id() {
        return disco_id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
    public void setDisco_id(Integer disco_id) {
        this.disco_id = disco_id;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "titulo='" + titulo + '\'' +
                ", duracion='" + duracion + '\'' +
                ", disco_id=" + disco_id +
                ", id=" + id +
                '}';
    }
}
