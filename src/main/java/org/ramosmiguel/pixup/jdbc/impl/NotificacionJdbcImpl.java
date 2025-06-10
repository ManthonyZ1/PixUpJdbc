
package org.ramosmiguel.pixup.jdbc.impl;

import org.ramosmiguel.pixup.model.Notificacion;
import org.ramosmiguel.pixup.jdbc.Conexion;
import org.ramosmiguel.pixup.jdbc.NotificacionJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NotificacionJdbcImpl extends Conexion<Notificacion> implements NotificacionJdbc {

    private static NotificacionJdbc notificacionJdbc;

    public NotificacionJdbcImpl() {}

    public static NotificacionJdbc getInstance( )
    {
        if (notificacionJdbc == null)
        {
            notificacionJdbc = new NotificacionJdbcImpl();
        }
        return notificacionJdbc;
    }

    @Override
    public List<Notificacion> findAll() {

        Statement statement = null;
        ResultSet resultSet = null;
        List<Notificacion> notificacions = null;
        Notificacion notificacion = null;
        String query = "SELECT * FROM TBL_NOTIFICACION";


        try
        {
            if( !openConnection() )
            {
                System.out.println("Error en conexión");
                return null;
            }

            statement = connection.createStatement( );
            resultSet = statement.executeQuery( query );
            notificacions = new ArrayList<>( );
            while( resultSet.next() )
            {
                notificacion = new Notificacion();
                notificacion.setId( resultSet.getInt( 1 ) );
                notificacion.setFechaNotificacion( resultSet.getString( 2 ) );
                notificacion.setUsuario_id( resultSet.getInt( 3 ) );
                notificacion.setId_tipo_notifacion( resultSet.getInt( 4 ) );
                notificacions.add( notificacion );
            }
            resultSet.close();
            statement.close();
            closeConnection( );
            return notificacions;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public boolean save(Notificacion notificacion) {

        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO TBL_NOTIFICACION (FECHA_NOTIFICACION, TBL_USUARIO_ID, TBL_TIPO_NOTIFICACION_ID) VALUES (?, ?, ?)";
        int res = 0;

        try {
            if (!openConnection()) {
                System.out.println("Error en Conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, notificacion.getFechaNotificacion( ) );
            preparedStatement.setInt(2, notificacion.getUsuario_id( ) );
            preparedStatement.setInt(3, notificacion.getId_tipo_notifacion( ) );
            res = preparedStatement.executeUpdate( );
            preparedStatement.close();
            closeConnection();
            return res == 1;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;

    }

    @Override
    public boolean update(Notificacion notificacion) {

        PreparedStatement preparedStatement = null;
        String query = "UPDATE TBL_NOTIFICACION SET FECHA_NOTIFICACION = ?, TBL_USUARIO_ID = ?, TBL_TIPO_NOTIFICACION_ID = ? WHERE ID = ?";
        int res = 0;

        try {
            if (!openConnection()) {
                System.out.println("Error en Conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, notificacion.getFechaNotificacion() );
            preparedStatement.setInt(2, notificacion.getUsuario_id() );
            preparedStatement.setInt(3, notificacion.getId_tipo_notifacion() );
            preparedStatement.setInt(4, notificacion.getId() );
            res = preparedStatement.executeUpdate( );
            preparedStatement.close();
            closeConnection();
            return res == 1;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;


    }

    @Override
    public boolean delete(Notificacion notificacion) {

        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM TBL_NOTIFICACION WHERE ID = ?";
        int res = 0;

        try {
            if (!openConnection())
            {
                System.out.println("Error en Conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, notificacion.getId( ) );
            res = preparedStatement.executeUpdate( );
            preparedStatement.close();
            closeConnection();
            return res == 1;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Notificacion findById(Integer id) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Notificacion notificacion = null;
        String query = "SELECT * FROM TBL_NOTIFICACION WHERE ID = ?";

        try
        {
            if( !openConnection() )
            {
                System.out.println("Error en conexión");
                return null;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery(  );

            if ( resultSet.next() )
            {
                notificacion = new Notificacion();
                notificacion.setId( resultSet.getInt( 1 ) );
                notificacion.setFechaNotificacion( resultSet.getString( 2 ) );
                notificacion.setUsuario_id( resultSet.getInt(3 ) );
                notificacion.setId_tipo_notifacion( resultSet.getInt(4 ) );
            }
            resultSet.close();
            preparedStatement.close();
            closeConnection( );
            return notificacion;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /*
    public static void main( String a[] )
    {
        NotificacionJdbcImpl
                .getInstance()
                .findAll()
                .stream()
                .forEach( System.out::println);
    }

*/


}