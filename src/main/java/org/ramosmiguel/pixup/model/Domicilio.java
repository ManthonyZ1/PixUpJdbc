package org.ramosmiguel.pixup.model;

public class Domicilio extends Catalogo
{
    private String calle;
    private String num_exterior;
    private String num_interior;

    public Domicilio()
    {
    }

    public String getCalle()
    {
        return calle;
    }

    public void setCalle(String calle)
    {
        this.calle = calle;
    }

    public String getNum_exterior()
    {
        return num_exterior;
    }

    public void setNum_exterior(String num_exterior)
    {
        this.num_exterior = num_exterior;
    }

    public String getNum_interior()
    {
        return num_interior;
    }

    public void setNum_interior(String num_interior)
    {
        this.num_interior = num_interior;
    }

    @Override
    public String toString()
    {
        return "Domicilio{" +
                "calle='" + calle + '\'' +
                ", num_exterior='" + num_exterior + '\'' +
                ", num_interior='" + num_interior + '\'' +
                ", id=" + id +
                '}';
    }
}
