package org.ramosmiguel.pixup.jdbc.impl;

import org.ramosmiguel.pixup.jdbc.Conexion;
import org.ramosmiguel.pixup.jdbc.DomicilioJdbc;
import org.ramosmiguel.pixup.model.Domicilio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DomicilioJdbcImpl extends Conexion<Domicilio> implements DomicilioJdbc
{
    private static DomicilioJdbcImpl domicilioJdbc;

    private DomicilioJdbcImpl()
    {
        super();
    }

    public static DomicilioJdbcImpl getInstance()
    {
        if (domicilioJdbc == null)
        {
            domicilioJdbc = new DomicilioJdbcImpl();
        }
        return domicilioJdbc;
    }

    @Override
    public List<Domicilio> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Domicilio> list = null;
        Domicilio domicilio = null;
        String sql = "SELECT * FROM TBL_DOMICILIO";

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
                domicilio = new Domicilio();
                domicilio.setId(resultSet.getInt("ID"));
                domicilio.setCalle(resultSet.getString("CALLE"));
                domicilio.setNum_exterior(resultSet.getString("NUM_EXTERIOR"));
                domicilio.setNum_interior(resultSet.getString("NUM_INTERIOR"));
                list.add(domicilio);
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
