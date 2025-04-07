package org.ramosmiguel.pixup.jdbc;

import org.ramosmiguel.pixup.model.Notificacion;

import java.util.List;

public interface NotificacionJdbc
{
    List<Notificacion> findAll();
}
