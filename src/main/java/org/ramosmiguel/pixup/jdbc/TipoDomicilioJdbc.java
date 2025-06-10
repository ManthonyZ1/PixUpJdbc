package org.ramosmiguel.pixup.jdbc;

import org.ramosmiguel.pixup.model.TipoDomicilio;

import java.util.List;

public interface TipoDomicilioJdbc {

    List<TipoDomicilio> findAll( );
    boolean save(TipoDomicilio tipoDomicilio);
    boolean update(TipoDomicilio tipoDomicilio);
    boolean delete(TipoDomicilio tipoDomicilio);
    TipoDomicilio findById(Integer id);


}