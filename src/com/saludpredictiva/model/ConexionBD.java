package com.saludpredictiva.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=SaludPredictiva;encrypt=true;trustServerCertificate=true";
    private static final String USUARIO = "admin123";
    private static final String CLAVE = "admin123";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}
