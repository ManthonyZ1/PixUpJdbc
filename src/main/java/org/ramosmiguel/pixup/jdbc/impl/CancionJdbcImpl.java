
package org.ramosmiguel.pixup.jdbc.impl;

import org.ramosmiguel.pixup.model.Cancion;
import org.ramosmiguel.pixup.jdbc.CancionJdbc;
import org.ramosmiguel.pixup.jdbc.Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CancionJdbcImpl extends Conexion<Cancion> implements CancionJdbc {


    private static CancionJdbc cancionJdbc;

    private CancionJdbcImpl() {
    }

    public static CancionJdbc getInstance()
    {
        if (cancionJdbc == null) {
            cancionJdbc = new CancionJdbcImpl();
        }
        return cancionJdbc;
    }

    @Override
    public List<Cancion> findAll() {

        Statement statement = null;
        ResultSet resultSet = null;
        List<Cancion> cancions = null;
        Cancion cancion = null;
        String query = "SELECT * FROM TBL_CANCION";

        try {
            if (!openConnection())
            {
                System.out.println("Error en conexión");
                return null;
            }

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            cancions = new ArrayList<>();

            while (resultSet.next())
            {
                cancion = new Cancion();
                cancion.setId(resultSet.getInt(1));
                cancion.setTitulo(resultSet.getString(2));
                cancion.setDuracion(resultSet.getString(3));
                cancion.setDisco_id(resultSet.getInt(4));
                cancions.add(cancion);
            }
            resultSet.close();
            statement.close();
            closeConnection();
            return cancions;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean save(Cancion cancion) {

        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO TBL_CANCION (TITULO, DURACION, TBL_DISCO_ID) VALUES (?, ?, ?)";
        int res = 0;

        try {
            if (!openConnection())
            {
                System.out.println("Error en conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cancion.getTitulo());
            preparedStatement.setString(2, cancion.getDuracion());
            preparedStatement.setInt(3, cancion.getDisco_id());
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
    public boolean update(Cancion cancion) {

        PreparedStatement preparedStatement = null;
        String query = "UPDATE TBL_CANCION SET TITULO = ?, DURACION = ?, TBL_DISCO_ID = ? WHERE ID = ?";
        int res = 0;

        try {
            if (!openConnection())
            {
                System.out.println("Error en conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cancion.getTitulo());
            preparedStatement.setString(2, cancion.getDuracion());
            preparedStatement.setInt(3, cancion.getDisco_id());
            preparedStatement.setInt(4, cancion.getId());
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
    public boolean delete(Cancion cancion) {

        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM TBL_CANCION WHERE ID = ?";
        int res = 0;

        try {
            if (!openConnection())
            {
                System.out.println("Error en conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, cancion.getId());
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
    public Cancion findById(Integer id) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Cancion cancion = null;
        String query = "SELECT * FROM TBL_CANCION WHERE ID = ?";

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
                cancion = new Cancion();
                cancion.setId(resultSet.getInt(1));
                cancion.setTitulo(resultSet.getString(2));
                cancion.setDuracion(resultSet.getString(3));
                cancion.setDisco_id(resultSet.getInt(4));
            }
            resultSet.close();
            preparedStatement.close();
            closeConnection();
            return cancion;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }



    /*
    public static void main(String[] a) {
        CancionJdbcImpl
                .getInstance()
                .findAll()
                .stream()
                .forEach(System.out::println);
    }

     */

}
