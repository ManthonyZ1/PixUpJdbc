package org.ramosmiguel.pixup.jdbc;

import org.ramosmiguel.pixup.model.Colonia;

import java.util.List;

public interface ColoniaJdbc
{
    List<Colonia> findAll();
    boolean save(Colonia colonia);
    boolean update(Colonia colonia);
    boolean delete(Colonia colonia);
    Colonia findById(Integer id);

}