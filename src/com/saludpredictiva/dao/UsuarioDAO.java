package com.saludpredictiva.dao;

import com.saludpredictiva.model.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    public boolean validarCredenciales(String correo, String contrasena) {
        String sql = "SELECT * FROM Usuario WHERE correo = ? AND contrasena = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, correo);
            stmt.setString(2, contrasena);

            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
