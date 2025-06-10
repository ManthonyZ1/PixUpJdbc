
package org.ramosmiguel.pixup.jdbc.impl;

import org.ramosmiguel.pixup.model.TipoNotificacion;
import org.ramosmiguel.pixup.jdbc.Conexion;
import org.ramosmiguel.pixup.jdbc.TipoNotificacionJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TipoNotificacionJdbcImpl extends Conexion<TipoNotificacion> implements TipoNotificacionJdbc {

    private static TipoNotificacionJdbc tipoNotificacionJdbc;

    public TipoNotificacionJdbcImpl() {}

    public static TipoNotificacionJdbc getInstance( )
    {
        if ( tipoNotificacionJdbc == null )
        {
            tipoNotificacionJdbc = new TipoNotificacionJdbcImpl();
        }
        return tipoNotificacionJdbc;
    }

    @Override
    public List<TipoNotificacion> findAll() {

        Statement statement = null;
        ResultSet resultSet = null;
        List<TipoNotificacion> tipoNotificacions = null;
        TipoNotificacion tipoNotificacion = null;
        String query = "SELECT * FROM TBL_TIPO_NOTIFICACION";

        try
        {
            if( !openConnection() )
            {
                System.out.println("Error en conexión");
                return null;
            }

            statement = connection.createStatement( );
            resultSet = statement.executeQuery( query );
            tipoNotificacions = new ArrayList<>( );
            while( resultSet.next() )
            {
                tipoNotificacion = new TipoNotificacion();
                tipoNotificacion.setId( resultSet.getInt( 1 ) );
                tipoNotificacion.setDescripcion( resultSet.getString( 2 ) );
                tipoNotificacion.setRutaPlantilla( resultSet.getString( 3 ) );
                tipoNotificacions.add( tipoNotificacion );
            }
            resultSet.close();
            statement.close();
            closeConnection( );
            return tipoNotificacions;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(TipoNotificacion tipoNotificacion) {

        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO TBL_TIPO_NOTIFICACION (DESCRIPCION, RUTA_PLANTILLA) VALUES (?, ?)";
        int res = 0;

        try {
            if (!openConnection())
            {
                System.out.println("Error en Conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tipoNotificacion.getDescripcion( ) );
            preparedStatement.setString(2, tipoNotificacion.getRutaPlantilla( ) );
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
    public boolean update(TipoNotificacion tipoNotificacion) {

        PreparedStatement preparedStatement = null;
        String query = "UPDATE TBL_TIPO_NOTIFICACION SET DESCRIPCION = ?, RUTA_PLANTILLA = ? WHERE ID = ?";
        int res = 0;

        try {
            if (!openConnection())
            {
                System.out.println("Error en Conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tipoNotificacion.getDescripcion() );
            preparedStatement.setString(2, tipoNotificacion.getRutaPlantilla());
            preparedStatement.setInt(3, tipoNotificacion.getId() );
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
    public boolean delete(TipoNotificacion tipoNotificacion) {

        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM TBL_TIPO_NOTIFICACION WHERE ID = ?";
        int res = 0;

        try {
            if (!openConnection())
            {
                System.out.println("Error en Conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, tipoNotificacion.getId( ) );
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
    public TipoNotificacion findById(Integer id) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        TipoNotificacion tipoNotificacion = null;
        String query = "SELECT * FROM TBL_TIPO_NOTIFICACION WHERE ID = ?";

        try {
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
                tipoNotificacion = new TipoNotificacion();
                tipoNotificacion.setId( resultSet.getInt( 1 ) );
                tipoNotificacion.setDescripcion( resultSet.getString( 2 ) );
                tipoNotificacion.setRutaPlantilla( resultSet.getString( 3) );
            }

            resultSet.close();
            preparedStatement.close();
            closeConnection( );
            return tipoNotificacion;
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
        TipoNotificacionJdbcImpl
                .getInstance()
                .findAll()
                .stream()
                .forEach( System.out::println);
    }

    */
}
