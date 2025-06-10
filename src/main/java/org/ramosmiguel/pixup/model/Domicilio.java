package org.ramosmiguel.pixup.model;

public class Domicilio extends Catalogo
{
    private String calle;
    private String num_exterior;
    private String num_interior;
    private Integer id_colonia;
    private Integer id_tipoDomicilio;
    private Integer id_usuario;


    public Domicilio() {}

    public String getCalle() {
        return calle;
    }
    public String getNumExterior() {
        return num_exterior;
    }
    public String getNumInterior() {
        return num_interior;
    }
    public Integer getColonia_id() {
        return id_colonia;
    }
    public Integer getTipoDomicilio_id() {
        return id_tipoDomicilio;
    }
    public Integer getUsuario_id() {
        return id_usuario;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }
    public void setNumExterior(String numExterior) {
        this.num_exterior = numExterior;
    }
    public void setNumInterior(String numInterior) {
        this.num_interior = numInterior;
    }
    public void setColonia_id(Integer colonia_id) {
        this.id_colonia = colonia_id;
    }
    public void setTipoDomicilio_id(Integer tipoDomicilio_id) {
        this.id_tipoDomicilio = tipoDomicilio_id;
    }
    public void setUsuario_id(Integer usuario_id) {
        this.id_usuario = usuario_id;
    }

    @Override
    public String toString() {
        return "Domicilio{" +
                "calle='" + calle + '\'' +
                ", numExterior='" + num_exterior + '\'' +
                ", numInterior='" + num_interior + '\'' +
                ", colonia_id=" + id_colonia +
                ", tipoDomicilio_id=" + id_tipoDomicilio +
                ", usuario_id=" + id_usuario +
                ", id=" + id +
                '}';
    }
}
