package com.saludpredictiva.view;

import javax.swing.*;
import java.awt.*;

public class EstadisticasTabbedForm extends JFrame {

    public EstadisticasTabbedForm() {
        setTitle("Estadísticas - SaludPredictiva");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(204, 229, 255)); // Celeste claro

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JPanel panelFacultad = new PanelCasosPorFacultad();      // Debe extender JPanel
        JPanel panelSintomas = new PanelSintomasFrecuentes();    // Debe extender JPanel
        JPanel panelTendencia = new PanelTendenciaTemporal();    // Debe extender JPanel

        tabbedPane.addTab("Casos por Facultad", panelFacultad);
        tabbedPane.addTab("Síntomas Más Reportados", panelSintomas);
        tabbedPane.addTab("Tendencia Temporal", panelTendencia);

        add(tabbedPane, BorderLayout.CENTER);
        setVisible(true);
    }
}
