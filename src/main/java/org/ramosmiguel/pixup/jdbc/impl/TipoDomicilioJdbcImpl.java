package org.ramosmiguel.pixup.jdbc.impl;

import org.ramosmiguel.pixup.model.TipoDomicilio;
import org.ramosmiguel.pixup.jdbc.Conexion;
import org.ramosmiguel.pixup.jdbc.TipoDomicilioJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TipoDomicilioJdbcImpl extends Conexion<TipoDomicilio> implements TipoDomicilioJdbc {

    private static TipoDomicilioJdbc tipoDomicilioJdbc;

    public TipoDomicilioJdbcImpl() {
    }

    public static TipoDomicilioJdbc getInstance( )
    {
        if ( tipoDomicilioJdbc == null)
        {
            tipoDomicilioJdbc = new TipoDomicilioJdbcImpl();
        }
        return tipoDomicilioJdbc;
    }

    @Override
    public List<TipoDomicilio> findAll() {

        Statement statement = null;
        ResultSet resultSet = null;
        List<TipoDomicilio>tipoDomicilios = null;
        TipoDomicilio tipoDomicilio = null;
        String query = "SELECT * FROM TBL_TIPO_DOMICILIO";

        try
        {
            if( !openConnection() )
            {
                System.out.println("Error en conexión");
                return null;
            }
            statement = connection.createStatement( );
            resultSet = statement.executeQuery( query );
            tipoDomicilios = new ArrayList<>( );
            while( resultSet.next() )
            {
                tipoDomicilio = new TipoDomicilio();
                tipoDomicilio.setId( resultSet.getInt( 1 ) );
                tipoDomicilio.setDescripcion( resultSet.getString( 2 ) );
                tipoDomicilios.add( tipoDomicilio );
            }
            resultSet.close();
            statement.close();
            closeConnection( );
            return tipoDomicilios;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public boolean save(TipoDomicilio tipoDomicilio) {

        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO TBL_TIPO_DOMICILIO (DESCRIPCION, TBL_DOMICILIO_ID) VALUES (?, ?)";
        int res = 0;

        try {
            if (!openConnection()) {
                System.out.println("Error en Conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tipoDomicilio.getDescripcion());
            preparedStatement.setInt(2, tipoDomicilio.getTblDomicilioId());
            res = preparedStatement.executeUpdate();

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
    public boolean update(TipoDomicilio tipoDomicilio) {

        PreparedStatement preparedStatement = null;
        String query = "UPDATE TBL_TIPO_DOMICILIO SET DESCRIPCION = ?, TBL_DOMICILIO_ID = ? WHERE ID = ?";
        int res = 0;

        try {
            if (!openConnection()) {
                System.out.println("Error en Conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tipoDomicilio.getDescripcion());
            preparedStatement.setInt(2, tipoDomicilio.getTblDomicilioId());
            preparedStatement.setInt(3, tipoDomicilio.getId());

            res = preparedStatement.executeUpdate();
            preparedStatement.close();
            closeConnection();

            return res == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(TipoDomicilio tipoDomicilio) {

        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM TBL_TIPO_DOMICILIO WHERE ID = ?";
        int res = 0;

        try {
            if (!openConnection()) {
                System.out.println("Error en Conexión");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, tipoDomicilio.getId( ) );
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
    public TipoDomicilio findById(Integer id) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        TipoDomicilio tipoDomicilio = null;
        String query = "SELECT * FROM TBL_TIPO_DOMICILIO WHERE ID = ?";

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
                tipoDomicilio = new TipoDomicilio();
                tipoDomicilio.setId( resultSet.getInt( 1 ) );
                tipoDomicilio.setDescripcion( resultSet.getString( 2 ) );

            }
            resultSet.close();
            preparedStatement.close();
            closeConnection( );
            return tipoDomicilio;
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
        TipoDomicilioJdbcImpl
                .getInstance()
                .findAll()
                .stream()
                .forEach( System.out::println);
    }

*/

}