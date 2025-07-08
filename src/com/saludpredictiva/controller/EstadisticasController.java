package com.saludpredictiva.controller;

import com.saludpredictiva.dao.EstadisticasDAO;
import org.jfree.chart.ChartPanel;

public class EstadisticasController {
    private EstadisticasDAO dao;

    public EstadisticasController() {
        dao = new EstadisticasDAO();
    }

    public ChartPanel graficoCasosPorFacultad() {
        return dao.generarGraficoCasosPorFacultad();
    }

    public ChartPanel graficoSintomasFrecuentes() {
        return dao.generarGraficoSintomasFrecuentes();
    }

    public ChartPanel graficoTendenciaTemporal() {
        return dao.generarGraficoTendenciaTemporal();
    }
}
