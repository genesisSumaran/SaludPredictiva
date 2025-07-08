package com.saludpredictiva.dao;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EstadisticasDAO {

    public ChartPanel generarGraficoCasosPorFacultad() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String query = "SELECT facultad, COUNT(*) AS cantidad FROM ReporteEpidemico GROUP BY facultad";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String facultad = rs.getString("facultad");
                int cantidad = rs.getInt("cantidad");
                System.out.println("Facultad: " + facultad + ", Cantidad: " + cantidad);
                if (facultad == null) {
                    facultad = "Desconocido";
                }
                dataset.addValue(cantidad, "Casos", facultad);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Casos por Facultad",
                "Facultad",
                "Cantidad de Casos",
                dataset
        );
        return new ChartPanel(chart);
    }

    public ChartPanel generarGraficoSintomasFrecuentes() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String query = "SELECT sintomas, COUNT(*) AS cantidad FROM ReporteEpidemico GROUP BY sintomas";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                dataset.addValue(rs.getInt("cantidad"), "Síntomas", rs.getString("sintomas"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Síntomas Más Reportados",
                "Síntoma",
                "Cantidad",
                dataset
        );
        return new ChartPanel(chart);
    }

    public ChartPanel generarGraficoTendenciaTemporal() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String query = "SELECT FORMAT(fecha, 'yyyy-MM-dd') AS dia, COUNT(*) AS cantidad FROM ReporteEpidemico GROUP BY FORMAT(fecha, 'yyyy-MM-dd') ORDER BY dia";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                dataset.addValue(rs.getInt("cantidad"), "Reportes", rs.getString("dia"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Tendencia de Reportes Diarios",
                "Fecha",
                "Cantidad de Reportes",
                dataset
        );
        return new ChartPanel(chart);
    }
}
