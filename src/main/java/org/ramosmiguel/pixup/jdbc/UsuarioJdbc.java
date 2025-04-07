package org.ramosmiguel.pixup.jdbc;

import org.ramosmiguel.pixup.model.Usuario;

import java.util.List;

public interface UsuarioJdbc
{
    List<Usuario> findAll();
}
