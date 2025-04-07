package org.ramosmiguel.pixup.jdbc;

import org.ramosmiguel.pixup.model.Estado;

import java.util.List;

public interface EstadoJdbc
{
    List<Estado> findAll( );
}
