package org.ramosmiguel.pixup.jdbc.impl;


import org.ramosmiguel.pixup.model.Municipio;
import org.ramosmiguel.pixup.jdbc.Conexion;
import org.ramosmiguel.pixup.jdbc.MunicipioJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MunicipioJdbcImpl extends Conexion<Municipio> implements MunicipioJdbc {
    private static MunicipioJdbc municipioJdbc;

    private MunicipioJdbcImpl() {
    }

    public static MunicipioJdbc getInstance() {
        if (municipioJdbc == null) {
            municipioJdbc = new MunicipioJdbcImpl();
        }
        return municipioJdbc;
    }

    @Override
    public List<Municipio> findAll() {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Municipio> municipios = null;
        Municipio municipio = null;
        String query = "SELECT * FROM TBL_MUNICIPIO";

        try {
            if (!openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            municipios = new ArrayList<>();
            while (resultSet.next()) {
                municipio = new Municipio();
                municipio.setId(resultSet.getInt(1));
                municipio.setMunicipio(resultSet.getString(2));
                municipio.setId_estado(resultSet.getInt(3));
                municipios.add(municipio);
            }
            resultSet.close();
            statement.close();
            closeConnection();
            return municipios;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Municipio municipio) {

        PreparedStatement preparedStatement = null; //Inicializa la variable prepareStatement como nula
        String query ="INSERT INTO TBL_MUNICIPIO (MUNICIPIO, TBL_ESTADO_ID) VALUES (? , ?)"; //Declarar la variable consulta (query) que indica insertar en la tabla_municipio
        int res = 0;

        try
        {
            if (!openConnection())
            {
                System.out.println("Error en Conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement( query );
            preparedStatement.setString(1, municipio.getMunicipio());
            preparedStatement.setInt(2, municipio.getId_estado());
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
    public boolean update(Municipio municipio) {

        PreparedStatement preparedStatement = null;
        String query = "UPDATE TBL_MUNICIPIO SET MUNICIPIO = ?, TBL_ESTADO_ID = ? WHERE ID = ?";
        int res = 0;

        try
        {
            if (!openConnection())
            {
                System.out.println("Error en conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, municipio.getMunicipio() );
            preparedStatement.setInt(2, municipio.getId_estado() );
            preparedStatement.setInt(3, municipio.getId() );
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
    public boolean delete(Municipio municipio) {

        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM TBL_MUNICIPIO WHERE ID = ?";
        int res = 0;

        try {
            if (!openConnection())
            {
                System.out.println("Error en conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement( query );
            preparedStatement.setInt(1, municipio.getId());
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
    public Municipio findById(Integer id) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Municipio municipio = null;
        String query = "SELECT * FROM TBL_MUNICIPIO WHERE ID = ?";

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
                municipio = new Municipio();
                municipio.setId( resultSet.getInt( 1 ) );
                municipio.setMunicipio( resultSet.getString( 2 ) );
                municipio.setId_estado(resultSet.getInt(3) );

            }
            resultSet.close();
            preparedStatement.close();
            closeConnection( );
            return municipio;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;

    }

    /*
    public static void main(String[] a) {
        MunicipioJdbcImpl
                .getInstance()
                .findAll()
                .stream()
                .forEach(System.out::println);
    }


     */
}