package org.ramosmiguel.pixup.jdbc.impl;

import org.ramosmiguel.pixup.jdbc.Conexion;
import org.ramosmiguel.pixup.jdbc.UsuarioJdbc;
import org.ramosmiguel.pixup.model.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UsuarioJdbcImpl extends Conexion<Usuario> implements UsuarioJdbc
{
    private static UsuarioJdbcImpl usuarioJdbc;

    private UsuarioJdbcImpl()
    {
        super();
    }

    public static UsuarioJdbcImpl getInstance()
    {
        if (usuarioJdbc == null)
        {
            usuarioJdbc = new UsuarioJdbcImpl();
        }
        return usuarioJdbc;
    }

    @Override
    public List<Usuario> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Usuario> list = null;
        Usuario usuario = null;
        String sql = "SELECT * FROM TBL_USUARIO";

        try
        {
            if (openConnection())
            {
                return null;
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet == null)
            {
                return null;
            }
            list = new java.util.ArrayList<>();
            while (resultSet.next())
            {
                usuario = new Usuario();
                usuario.setId(resultSet.getInt("ID"));
                usuario.setNombre(resultSet.getString("NOMBRE"));
                usuario.setPrimerApellido(resultSet.getString("PRIMER_APELLIDO"));
                usuario.setSegundoApellido(resultSet.getString("SEGUNDO_APELLIDO"));
                usuario.setPassword(resultSet.getString("PASSWORD"));
                usuario.setEmail(resultSet.getString("EMAIL"));
                usuario.setRfc(resultSet.getString("RFC"));
                list.add(usuario);
            }
            resultSet.close();
            closeConnection();
            return list;
        }
        catch (SQLException e)
        {
            return null;
        }
    }
}
