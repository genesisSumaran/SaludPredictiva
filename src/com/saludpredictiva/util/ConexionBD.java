package com.saludpredictiva.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=SaludPredictiva;encrypt=false";
    private static final String USUARIO = "admin123";
    private static final String CONTRASENA = "admin123";

    public static Connection obtenerConexion() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}
