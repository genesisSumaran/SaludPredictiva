package com.saludpredictiva.dao;

import com.saludpredictiva.model.ReporteEpidemico;
import com.saludpredictiva.util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReporteEpidemicoDAO {

    public boolean insertar(ReporteEpidemico reporte) {
        boolean resultado = false;
        try {
            Connection conn = ConexionBD.obtenerConexion();
            String sql = "INSERT INTO ReporteEpidemico (facultad, turno, ciclo, sintomas, cantidadCasos, fecha, nombrePaciente, duracionSintomas, diagnosticoProbable) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, reporte.getFacultad());
            ps.setString(2, reporte.getTurno());
            ps.setInt(3, reporte.getCiclo());
            ps.setString(4, reporte.getSintomas());
            ps.setInt(5, reporte.getCantidadCasos());
            ps.setDate(6, reporte.getFecha());
            ps.setString(7, reporte.getNombrePaciente());
            ps.setInt(8, reporte.getDuracionSintomas());
            ps.setString(9, reporte.getDiagnosticoProbable());
            resultado = ps.executeUpdate() > 0;
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public List<ReporteEpidemico> obtenerTodos() {
        List<ReporteEpidemico> lista = new ArrayList<>();
        try {
            Connection conn = ConexionBD.obtenerConexion();
            String sql = "SELECT * FROM ReporteEpidemico";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ReporteEpidemico r = new ReporteEpidemico(
                    rs.getInt("id"),
                    rs.getString("facultad"),
                    rs.getString("turno"),
                    rs.getInt("ciclo"),
                    rs.getString("sintomas"),
                    rs.getInt("cantidadCasos"),
                    rs.getDate("fecha"),
                    rs.getString("nombrePaciente"),
                    rs.getInt("duracionSintomas"),
                    rs.getString("diagnosticoProbable")
                );
                lista.add(r);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
