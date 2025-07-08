package com.saludpredictiva.view;

import com.saludpredictiva.controller.ReporteEpidemicoController;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class PanelSintomasFrecuentes extends JPanel {

    public PanelSintomasFrecuentes() {
        setLayout(new BorderLayout());
        setBackground(new Color(204, 229, 255));

        ReporteEpidemicoController controller = new ReporteEpidemicoController();
        Map<String, Integer> datos = controller.obtenerSintomasMasReportados();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (String sintoma : datos.keySet()) {
            dataset.addValue(datos.get(sintoma), "Síntomas", sintoma);
        }

        JFreeChart chart = ChartFactory.createBarChart("Síntomas Más Reportados", "Síntoma", "Frecuencia", dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
    }
}
