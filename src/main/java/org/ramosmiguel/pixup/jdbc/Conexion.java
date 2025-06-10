package org.ramosmiguel.pixup.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Conexion<T> {

    private static final String USER = "root";                // Cambia si usas otro usuario
    private static final String PASSWORD = "ManthonyZ1#3";    // Asegúrate que sea la correcta
    private static final String DB = "pixup";
    private static final String SERVER = "127.0.0.1";
    private static final String URL = "jdbc:mysql://" + SERVER + "/" + DB + "?useSSL=false&serverTimezone=UTC" + "&allowPublicKeyRetrieval=true";

    protected Connection connection;

    public Conexion() {}

    protected boolean testDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return true;
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver no encontrado.");
            e.printStackTrace();
            return false;
        }
    }

    protected boolean loadConnection() {
        try {
            if (!testDriver()) {
                return false;
            }
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return true;
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos:");
            e.printStackTrace();
            return false;
        }
    }

    public boolean openConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                return loadConnection();
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error al verificar la conexión:");
            e.printStackTrace();
            return false;
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión:");
            e.printStackTrace();
        }
    }
}
