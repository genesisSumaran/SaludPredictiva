package com.saludpredictiva.controller;

import com.saludpredictiva.dao.ReporteEpidemicoDAO;
import com.saludpredictiva.model.ReporteEpidemico;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReporteEpidemicoController {
    private ReporteEpidemicoDAO dao;

    public ReporteEpidemicoController() {
        dao = new ReporteEpidemicoDAO();
    }

    public boolean registrarReporte(ReporteEpidemico reporte) {
        return dao.insertar(reporte);
    }

    public List<ReporteEpidemico> obtenerTodosLosReportes() {
        return dao.obtenerTodos();
    }

    public Map<String, Integer> obtenerCasosPorFacultad() {
        Map<String, Integer> datos = new HashMap<>();
        for (ReporteEpidemico reporte : dao.obtenerTodos()) {
            String facultad = reporte.getFacultad();
            int cantidad = datos.getOrDefault(facultad, 0) + reporte.getCantidadCasos();
            datos.put(facultad, cantidad);
        }
        return datos;
    }

    public Map<String, Integer> obtenerSintomasMasReportados() {
        Map<String, Integer> datos = new HashMap<>();
        for (ReporteEpidemico reporte : dao.obtenerTodos()) {
            String[] sintomas = reporte.getSintomas().split(",");
            for (String sintoma : sintomas) {
                sintoma = sintoma.trim();
                datos.put(sintoma, datos.getOrDefault(sintoma, 0) + 1);
            }
        }
        return datos;
    }

    public Map<String, Integer> obtenerTendenciaReportes() {
        Map<String, Integer> datos = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (ReporteEpidemico reporte : dao.obtenerTodos()) {
            String fecha = sdf.format(reporte.getFecha());
            datos.put(fecha, datos.getOrDefault(fecha, 0) + 1);
        }
        return datos;
    }
}
