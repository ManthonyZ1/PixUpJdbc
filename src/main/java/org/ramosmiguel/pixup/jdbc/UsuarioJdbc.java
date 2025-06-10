package org.ramosmiguel.pixup.jdbc;

import org.ramosmiguel.pixup.model.Usuario;

import java.util.List;

public interface UsuarioJdbc
{

    List<Usuario> findAll();
    boolean save(Usuario usuario);
    boolean update(Usuario usuario);
    boolean delete(Usuario usuario);
    Usuario findById(Integer id);


}
