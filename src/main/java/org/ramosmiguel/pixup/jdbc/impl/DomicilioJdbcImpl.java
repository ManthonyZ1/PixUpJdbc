package org.ramosmiguel.pixup.jdbc.impl;

import org.ramosmiguel.pixup.model.Domicilio;
import org.ramosmiguel.pixup.jdbc.Conexion;
import org.ramosmiguel.pixup.jdbc.DomicilioJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DomicilioJdbcImpl extends Conexion<Domicilio> implements DomicilioJdbc {

    private static DomicilioJdbc domicilioJdbc;

    private DomicilioJdbcImpl() {}

    public static DomicilioJdbc getInstance() {
        if (domicilioJdbc == null)
        {
            domicilioJdbc = new DomicilioJdbcImpl();
        }
        return domicilioJdbc;
    }

    @Override
    public List<Domicilio> findAll() {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Domicilio> domicilios = null;
        Domicilio domicilio = null;
        String query = "SELECT * FROM TBL_DOMICILIO";

        try {
            if (!openConnection())
            {
                System.out.println("Error en conexión");
                return null;
            }

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            domicilios = new ArrayList<>();
            while (resultSet.next())
            {
                domicilio = new Domicilio();
                domicilio.setId(resultSet.getInt(1));
                domicilio.setCalle(resultSet.getString(2));
                domicilio.setNumExterior(resultSet.getString(3));
                domicilio.setNumInterior(resultSet.getString(4));
                domicilio.setColonia_id(resultSet.getInt(5));
                domicilio.setUsuario_id(resultSet.getInt(6));
                domicilios.add(domicilio);
            }
            resultSet.close();
            statement.close();
            closeConnection();
            return domicilios;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean save(Domicilio domicilio) {

        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO TBL_DOMICILIO (CALLE, NUM_EXTERIOR, NUM_INTERIOR, TBL_COLONIA_ID, TBL_USUARIO_ID) VALUES (?, ?, ?, ?, ?)";
        int res = 0;

        try {
            if (!openConnection())
            {
                System.out.println("Error en conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setString(2, domicilio.getNumExterior());
            preparedStatement.setString(3, domicilio.getNumInterior());
            preparedStatement.setInt(4, domicilio.getColonia_id());
            preparedStatement.setInt(5, domicilio.getUsuario_id());
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
    public boolean update(Domicilio domicilio) {

        PreparedStatement preparedStatement = null;
        String query = "UPDATE TBL_DOMICILIO SET CALLE = ?, NUM_EXTERIOR = ?, NUM_INTERIOR = ?, TBL_COLONIA_ID = ?, TBL_USUARIO_ID = ? WHERE ID = ?";
        int res = 0;

        try {
            if (!openConnection())
            {
                System.out.println("Error en conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setString(2, domicilio.getNumExterior());
            preparedStatement.setString(3, domicilio.getNumInterior());
            preparedStatement.setInt(4, domicilio.getColonia_id());
            preparedStatement.setInt(5, domicilio.getUsuario_id());
            preparedStatement.setInt(6, domicilio.getId());
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
    public boolean delete(Domicilio domicilio) {

        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM TBL_DOMICILIO WHERE ID = ?";
        int res = 0;
        try {
            if (!openConnection())
            {
                System.out.println("Error en conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, domicilio.getId());
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
    public Domicilio findById(Integer id) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Domicilio domicilio = null;

        String query = "SELECT * FROM TBL_DOMICILIO WHERE ID = ?";
        try {
            if (!openConnection())
            {
                System.out.println("Error en conexión");
                return null;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                domicilio = new Domicilio();
                domicilio.setId(resultSet.getInt(1));
                domicilio.setCalle(resultSet.getString(2));
                domicilio.setNumExterior(resultSet.getString(3));
                domicilio.setNumInterior(resultSet.getString(4));
                domicilio.setColonia_id(resultSet.getInt(5));
                domicilio.setUsuario_id(resultSet.getInt(6));
            }

            resultSet.close();
            preparedStatement.close();
            closeConnection();
            return domicilio;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /*
    public static void main(String[] a) {
        DomicilioJdbcImpl
                .getInstance()
                .findAll()
                .stream()
                .forEach(System.out::println);
    }

     */
}