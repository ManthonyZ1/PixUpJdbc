package org.ramosmiguel.pixup.model;

public class TipoNotificacion extends Catalogo
{
    private String descripcion;
    private String rutaPlantilla;

    public TipoNotificacion()
    {
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public String getRutaPlantilla()
    {
        return rutaPlantilla;
    }

    public void setRutaPlantilla(String rutaPlantilla)
    {
        this.rutaPlantilla = rutaPlantilla;
    }

    @Override
    public String toString()
    {
        return "TipoNotificacion{" +
                "descripcion='" + descripcion + '\'' +
                ", rutaPlantilla='" + rutaPlantilla + '\'' +
                ", id=" + id +
                '}';
    }
}
