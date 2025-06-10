package org.ramosmiguel.pixup.model;

public class Disco extends Catalogo
{

    private String titulo;
    private Integer precio;
    private Integer existencia;
    private Integer descuento;
    private String fechaLanzamiento;
    private String imagen;
    private Integer artista_id;
    private Integer disquera_id;
    private Integer generoMusical_id;

    public Disco() {
    }

    public String getTitulo() {
        return titulo;
    }
    public Integer getPrecio() {
        return precio;
    }
    public Integer getExistencia() {
        return existencia;
    }
    public Integer getDescuento() {
        return descuento;
    }
    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }
    public String getImagen() {
        return imagen;
    }
    public Integer getArtista_id() {
        return artista_id;
    }
    public Integer getDisquera_id() {
        return disquera_id;
    }
    public Integer getGeneroMusical_id() {
        return generoMusical_id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setPrecio(Integer precio) {
        this.precio = precio;
    }
    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }
    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }
    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public void setArtista_id(Integer artista_id) {
        this.artista_id = artista_id;
    }
    public void setDisquera_id(Integer disquera_id) {
        this.disquera_id = disquera_id;
    }
    public void setGeneroMusical_id(Integer generoMusical_id) {
        this.generoMusical_id = generoMusical_id;
    }

    @Override
    public String toString() {
        return "Disco{" +
                "titulo='" + titulo + '\'' +
                ", precio=" + precio +
                ", existencia=" + existencia +
                ", descuento=" + descuento +
                ", fechaLanzamiento=" + fechaLanzamiento +
                ", imagen='" + imagen + '\'' +
                ", artista_id=" + artista_id +
                ", disquera_id=" + disquera_id +
                ", generoMusical_id=" + generoMusical_id +
                ", id=" + id +
                '}';
    }
}