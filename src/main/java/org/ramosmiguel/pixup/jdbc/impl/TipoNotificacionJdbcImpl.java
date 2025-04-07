package org.ramosmiguel.pixup.jdbc.impl;

import org.ramosmiguel.pixup.jdbc.Conexion;
import org.ramosmiguel.pixup.jdbc.TipoNotificacionJdbc;
import org.ramosmiguel.pixup.model.TipoNotificacion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TipoNotificacionJdbcImpl extends Conexion<TipoNotificacion> implements TipoNotificacionJdbc
{
    private static TipoNotificacionJdbcImpl tipoNotificacionJdbc;

    private TipoNotificacionJdbcImpl()
    {
        super();
    }

    public static TipoNotificacionJdbcImpl getInstance()
    {
        if (tipoNotificacionJdbc == null)
        {
            tipoNotificacionJdbc = new TipoNotificacionJdbcImpl();
        }
        return tipoNotificacionJdbc;
    }

    @Override
    public List<TipoNotificacion> findAll()
    {
        Statement statement;
        ResultSet resultSet;
        List<TipoNotificacion> list = null;
        TipoNotificacion tipoNotificacion;
        String sql = "SELECT * FROM TBL_TIPO_NOTIFICACION";

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
                tipoNotificacion = new TipoNotificacion();
                tipoNotificacion.setId(resultSet.getInt("ID"));
                tipoNotificacion.setDescripcion(resultSet.getString("DESCRIPCION"));
                tipoNotificacion.setRutaPlantilla(resultSet.getString("RUTA_PLANTILLA"));
                list.add(tipoNotificacion);
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
