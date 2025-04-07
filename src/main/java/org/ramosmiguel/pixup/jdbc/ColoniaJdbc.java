package org.ramosmiguel.pixup.jdbc;

import org.ramosmiguel.pixup.model.Colonia;

import java.util.List;

public interface ColoniaJdbc
{
    List<Colonia> findAll();
}
