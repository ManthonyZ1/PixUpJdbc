package org.ramosmiguel.pixup.jdbc;

import org.ramosmiguel.pixup.model.TipoNotificacion;

import java.util.List;

public interface TipoNotificacionJdbc
{
    List<TipoNotificacion> findAll();
}
