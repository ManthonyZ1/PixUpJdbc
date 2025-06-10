package org.ramosmiguel.pixup.jdbc;

import org.ramosmiguel.pixup.model.Disquera;

import java.util.List;

public interface DisqueraJdbc
{

    List<Disquera> findAll();
    boolean save(Disquera disquera);
    boolean update(Disquera disquera);
    boolean delete(Disquera disquera);
    Disquera findById(Integer id);

}