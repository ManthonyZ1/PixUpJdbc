package org.ramosmiguel.pixup.jdbc.impl;

import org.ramosmiguel.pixup.jdbc.Conexion;
import org.ramosmiguel.pixup.jdbc.TipoDomicilioJdbc;
import org.ramosmiguel.pixup.model.TipoDomicilio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TipoDomicilioJdbcImpl extends Conexion<TipoDomicilio> implements TipoDomicilioJdbc
{
    private static TipoDomicilioJdbcImpl tipoDomicilioJdbc;

    private TipoDomicilioJdbcImpl()
    {
        super();
    }

    public static TipoDomicilioJdbcImpl getInstance()
    {
        if (tipoDomicilioJdbc == null)
        {
            tipoDomicilioJdbc = new TipoDomicilioJdbcImpl();
        }
        return tipoDomicilioJdbc;
    }

    @Override
    public List<TipoDomicilio> findAll()
    {
        Statement statement;
        ResultSet resultSet;
        List<TipoDomicilio> list = null;
        TipoDomicilio tipoDomicilio;
        String sql = "SELECT * FROM TBL_TIPO_DOMICILIO";

        try
        {
            if (openConnection())
            {
                return null;
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            list = new java.util.ArrayList<>();
            while (resultSet.next())
            {
                tipoDomicilio = new TipoDomicilio();
                tipoDomicilio.setId(resultSet.getInt("ID"));
                tipoDomicilio.setDescripcion(resultSet.getString("DESCRIPCION"));
                list.add(tipoDomicilio);
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
