package org.ramosmiguel.pixup.jdbc;

import org.ramosmiguel.pixup.model.Notificacion;

import java.util.List;

public interface NotificacionJdbc {

    List<Notificacion> findAll( );
    boolean save(Notificacion notificacion);
    boolean update(Notificacion notificacion);
    boolean delete(Notificacion notificacion);
    Notificacion findById(Integer id);


}