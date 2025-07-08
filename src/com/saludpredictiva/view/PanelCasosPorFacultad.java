package com.saludpredictiva.view;

import com.saludpredictiva.controller.ReporteEpidemicoController;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class PanelCasosPorFacultad extends JPanel {

    public PanelCasosPorFacultad() {
        setLayout(new BorderLayout());
        setBackground(new Color(204, 229, 255));

        ReporteEpidemicoController controller = new ReporteEpidemicoController();
        Map<String, Integer> datos = controller.obtenerCasosPorFacultad();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (String facultad : datos.keySet()) {
            dataset.addValue(datos.get(facultad), "Casos", facultad);
        }

        JFreeChart chart = ChartFactory.createBarChart("Casos por Facultad", "Facultad", "Cantidad", dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
    }
}
