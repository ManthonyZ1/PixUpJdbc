package org.ramosmiguel.pixup.jdbc.impl;

import org.ramosmiguel.pixup.jdbc.Conexion;
import org.ramosmiguel.pixup.jdbc.ColoniaJdbc;
import org.ramosmiguel.pixup.model.Colonia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ColoniaJdbcImpl extends Conexion<Colonia> implements ColoniaJdbc
{
    private static ColoniaJdbcImpl coloniaJdbc;

    private ColoniaJdbcImpl()
    {
        super();
    }

    public static ColoniaJdbcImpl getInstance()
    {
        if (coloniaJdbc == null)
        {
            coloniaJdbc = new ColoniaJdbcImpl();
        }
        return coloniaJdbc;
    }

    @Override
    public List<Colonia> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Colonia> list = null;
        Colonia colonia = null;
        String sql = "SELECT * FROM TBL_COLONIA";

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
                colonia = new Colonia();
                colonia.setId(resultSet.getInt("ID"));
                colonia.setNombre(resultSet.getString("COLONIA"));
                colonia.setCp(resultSet.getString("CP"));
                list.add(colonia);
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
