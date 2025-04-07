package org.ramosmiguel.pixup.model;

import java.util.Date;

public class Notificacion extends Catalogo
{
    private Date fechaNotificacion;

    public Notificacion()
    {
    }

    public Date getFechaNotificacion()
    {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion)
    {
        this.fechaNotificacion = fechaNotificacion;
    }

    @Override
    public String toString()
    {
        return "Notificacion{" +
                "fechaNotificacion=" + fechaNotificacion +
                ", id=" + id +
                '}';
    }
}
