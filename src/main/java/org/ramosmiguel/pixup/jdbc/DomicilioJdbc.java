package org.ramosmiguel.pixup.jdbc;

import org.ramosmiguel.pixup.model.Domicilio;

import java.util.List;

public interface DomicilioJdbc
{

    List<Domicilio> findAll();
    boolean save(Domicilio domicilio);
    boolean update(Domicilio domicilio);
    boolean delete(Domicilio domicilio);
    Domicilio findById(Integer id);


}