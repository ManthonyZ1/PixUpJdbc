package org.ramosmiguel.pixup.jdbc;

import org.ramosmiguel.pixup.model.Municipio;

import java.util.List;

public interface MunicipioJdbc
{
    List<Municipio> findAll();
}
