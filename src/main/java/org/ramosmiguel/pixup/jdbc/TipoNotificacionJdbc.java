package org.ramosmiguel.pixup.jdbc;

import org.ramosmiguel.pixup.model.TipoNotificacion;

import java.util.List;

public interface TipoNotificacionJdbc {

    List<TipoNotificacion> findAll( );
    boolean save(TipoNotificacion tipoNotificacion);
    boolean update(TipoNotificacion tipoNotificacion);
    boolean delete(TipoNotificacion tipoNotificacion);
    TipoNotificacion findById(Integer id);

}