package org.ramosmiguel.pixup.jdbc;

import org.ramosmiguel.pixup.model.Estado;

import java.util.List;

public interface EstadoJdbc
{
    List<Estado> findAll( );
    boolean save(Estado estado);
    boolean update(Estado estado);
    boolean delete(Estado estado);
    Estado findById(Integer id);

}