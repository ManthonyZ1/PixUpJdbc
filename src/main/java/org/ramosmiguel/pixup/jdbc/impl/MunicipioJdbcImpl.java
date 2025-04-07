package org.ramosmiguel.pixup.jdbc.impl;

import org.ramosmiguel.pixup.jdbc.Conexion;
import org.ramosmiguel.pixup.jdbc.MunicipioJdbc;
import org.ramosmiguel.pixup.model.Municipio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MunicipioJdbcImpl extends Conexion<Municipio> implements MunicipioJdbc
{
    private static MunicipioJdbcImpl municipioJdbc;

    private MunicipioJdbcImpl()
    {
        super();
    }

    public static MunicipioJdbcImpl getInstance()
    {
        if (municipioJdbc == null)
        {
            municipioJdbc = new MunicipioJdbcImpl();
        }
        return municipioJdbc;
    }

    @Override
    public List<Municipio> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Municipio> list = null;
        Municipio municipio = null;
        String sql = "SELECT * FROM TBL_MUNICIPIO";

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
                municipio = new Municipio();
                municipio.setId(resultSet.getInt("ID"));
                municipio.setNombre(resultSet.getString("MUNICIPIO"));
                list.add(municipio);
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
