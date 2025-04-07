package org.ramosmiguel.pixup.jdbc;

import org.ramosmiguel.pixup.model.Domicilio;

import java.util.List;

public interface DomicilioJdbc
{
    List<Domicilio> findAll();
}
