package com.saludpredictiva.view;

import com.saludpredictiva.controller.ReporteEpidemicoController;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class PanelTendenciaTemporal extends JPanel {

    public PanelTendenciaTemporal() {
        setLayout(new BorderLayout());
        setBackground(new Color(204, 229, 255));

        ReporteEpidemicoController controller = new ReporteEpidemicoController();
        Map<String, Integer> datos = controller.obtenerTendenciaReportes();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (String fecha : datos.keySet()) {
            dataset.addValue(datos.get(fecha), "Reportes", fecha);
        }

        JFreeChart chart = ChartFactory.createLineChart("Tendencia de Reportes", "Fecha", "Cantidad", dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
    }
}
