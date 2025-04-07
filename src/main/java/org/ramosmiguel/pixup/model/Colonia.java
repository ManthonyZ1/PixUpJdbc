package org.ramosmiguel.pixup.model;

public class Colonia extends Catalogo
{
    private String nombre;
    private String cp;

    public Colonia()
    {
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getCp()
    {
        return cp;
    }

    public void setCp(String cp)
    {
        this.cp = cp;
    }

    @Override
    public String toString()
    {
        return "Colonia{" +
                "nombre='" + nombre + '\'' +
                ", cp='" + cp + '\'' +
                ", id=" + id +
                '}';
    }
}
