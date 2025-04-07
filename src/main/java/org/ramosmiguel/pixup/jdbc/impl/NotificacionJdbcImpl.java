package org.ramosmiguel.pixup.jdbc.impl;

import org.ramosmiguel.pixup.jdbc.Conexion;
import org.ramosmiguel.pixup.jdbc.NotificacionJdbc;
import org.ramosmiguel.pixup.model.Notificacion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class NotificacionJdbcImpl extends Conexion<Notificacion> implements NotificacionJdbc
{
    private static NotificacionJdbcImpl notificacionJdbc;

    private NotificacionJdbcImpl()
    {
        super();
    }

    public static NotificacionJdbcImpl getInstance()
    {
        if (notificacionJdbc == null)
        {
            notificacionJdbc = new NotificacionJdbcImpl();
        }
        return notificacionJdbc;
    }

    @Override
    public List<Notificacion> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Notificacion> list = null;
        Notificacion notificacion = null;
        String sql = "SELECT * FROM TBL_NOTIFICACION";

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
                notificacion = new Notificacion();
                notificacion.setId(resultSet.getInt("ID"));
                notificacion.setFechaNotificacion(resultSet.getDate("FECHA_NOTIFICACION"));
                list.add(notificacion);
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
