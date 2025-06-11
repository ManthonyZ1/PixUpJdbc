package org.ramosmiguel.pixup.jdbc.impl;


import org.ramosmiguel.pixup.model.Disquera;
import org.ramosmiguel.pixup.jdbc.Conexion;
import org.ramosmiguel.pixup.jdbc.DisqueraJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DisqueraJdbcImpl extends Conexion<Disquera> implements DisqueraJdbc {
    private static DisqueraJdbc disqueraJdbc;

    private DisqueraJdbcImpl() {
    }

    public static DisqueraJdbc getInstance() {
        if (disqueraJdbc == null)
        {
            disqueraJdbc = new DisqueraJdbcImpl();
        }
        return disqueraJdbc;
    }

    @Override
    public List<Disquera> findAll() {

        Statement statement = null;
        ResultSet resultSet = null;
        List<Disquera> disqueras = null;
        Disquera disquera = null;
        String query = "SELECT * FROM TBL_DISQUERA";

        try {
            if (!openConnection())
            {
                System.out.println("Error en conexión");
                return null;
            }

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            disqueras = new ArrayList<>();
            while (resultSet.next())
            {
                disquera = new Disquera();
                disquera.setId(resultSet.getInt(1));
                disquera.setNombre(resultSet.getString(2));
                disqueras.add(disquera);
            }
            resultSet.close();
            statement.close();
            closeConnection();
            return disqueras;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean save(Disquera disquera) {
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        String query = "INSERT INTO TBL_DISQUERA (NOMBRE) VALUES (?)";
        int res = 0;

        try {
            if (!openConnection()) {
                System.out.println("Error en conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, disquera.getNombre());

            res = preparedStatement.executeUpdate();

            if (res == 1) {
                generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    disquera.setId(generatedKeys.getInt(1)); // ← Aquí se guarda el ID
                }
            }

            if (generatedKeys != null) generatedKeys.close();
            preparedStatement.close();
            closeConnection();
            return res == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(Disquera disquera) {

        PreparedStatement preparedStatement = null;
        String query = "UPDATE TBL_DISQUERA SET NOMBRE = ? WHERE ID = ?";
        int res = 0;

        try {
            if (!openConnection())
            {
                System.out.println("Error en conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, disquera.getNombre());
            preparedStatement.setInt(2, disquera.getId());
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
    public boolean delete(Disquera disquera) {

        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM TBL_DISQUERA WHERE ID = ?";
        int res = 0;

        try {
            if (!openConnection())
            {
                System.out.println("Error en conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, disquera.getId());
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
    public Disquera findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Disquera disquera = null;
        String query = "SELECT * FROM TBL_DISQUERA WHERE ID = ?";

        try {
            if (!openConnection())
            {
                System.out.println("Error en conexión");
                return null;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                disquera = new Disquera();
                disquera.setId(resultSet.getInt(1));
                disquera.setNombre(resultSet.getString(2));
            }
            resultSet.close();
            preparedStatement.close();
            closeConnection();
            return disquera;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }



/*
    public static void main(String[] a) {
        DisqueraJdbcImpl
                .getInstance()
                .findAll()
                .stream()
                .forEach(System.out::println);
    }

 */
}