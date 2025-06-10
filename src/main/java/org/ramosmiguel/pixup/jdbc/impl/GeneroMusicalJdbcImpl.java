package org.ramosmiguel.pixup.jdbc.impl;

import org.ramosmiguel.pixup.model.GeneroMusical;
import org.ramosmiguel.pixup.jdbc.Conexion;
import org.ramosmiguel.pixup.jdbc.GeneroMusicalJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GeneroMusicalJdbcImpl extends Conexion<GeneroMusical> implements GeneroMusicalJdbc {

    private static GeneroMusicalJdbc generoMusicalJdbc;

    private GeneroMusicalJdbcImpl() {
    }

    public static GeneroMusicalJdbc getInstance() {
        if (generoMusicalJdbc == null)
        {
            generoMusicalJdbc = new GeneroMusicalJdbcImpl();
        }
        return generoMusicalJdbc;
    }

    @Override
    public List<GeneroMusical> findAll() {

        Statement statement = null;
        ResultSet resultSet = null;
        List<GeneroMusical> generosMusicales = null;
        GeneroMusical generoMusical = null;
        String query = "SELECT * FROM TBL_GENERO_MUSICAL";

        try {
            if (!openConnection())
            {
                System.out.println("Error en conexión");
                return null;
            }

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            generosMusicales = new ArrayList<>();

            while (resultSet.next())
            {
                generoMusical = new GeneroMusical();
                generoMusical.setId(resultSet.getInt(1));
                generoMusical.setDescripcion(resultSet.getString(2));
                generosMusicales.add(generoMusical);
            }
            resultSet.close();
            statement.close();
            closeConnection();
            return generosMusicales;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean save(GeneroMusical generoMusical) {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO TBL_GENERO_MUSICAL (ID, DESCRIPCION) VALUES (?, ?)";
        int res = 0;

        try {
            if (!openConnection()) {
                System.out.println("Error en conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, generoMusical.getId());           // ID manual
            preparedStatement.setString(2, generoMusical.getDescripcion());
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
    public boolean update(GeneroMusical generoMusical) {

        PreparedStatement preparedStatement = null;
        String query = "UPDATE TBL_GENERO_MUSICAL SET DESCRIPCION = ? WHERE ID = ?";
        int res = 0;

        try {
            if (!openConnection())
            {
                System.out.println("Error en conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, generoMusical.getDescripcion());
            preparedStatement.setInt(2, generoMusical.getId());
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
    public boolean delete(GeneroMusical generoMusical) {

        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM TBL_GENERO_MUSICAL WHERE ID = ?";
        int res = 0;

        try {
            if (!openConnection())
            {
                System.out.println("Error en conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, generoMusical.getId());
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
    public GeneroMusical findById(Integer id) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        GeneroMusical generoMusical = null;
        String query = "SELECT * FROM TBL_GENERO_MUSICAL WHERE ID = ?";

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
                generoMusical = new GeneroMusical();
                generoMusical.setId(resultSet.getInt(1));
                generoMusical.setDescripcion(resultSet.getString(2));
            }
            resultSet.close();
            preparedStatement.close();
            closeConnection();
            return generoMusical;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }


/*
    public static void main(String[] a) {
        GeneroMusicalJdbcImpl
                .getInstance()
                .findAll()
                .stream()
                .forEach(System.out::println);
    }

 */
}