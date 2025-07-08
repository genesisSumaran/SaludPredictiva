package com.saludpredictiva.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

public class DashboardForm extends JFrame {
    private JButton registrarButton;
    private JButton verButton;
    private JButton estadisticasButton;
    private JButton salirButton;

    public DashboardForm() {
        setTitle("Panel Principal - SaludPredictiva");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(204, 229, 255));

        JLabel titulo = new JLabel("Panel de Control");
        titulo.setBounds(160, 20, 200, 30);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(new Color(0, 51, 102));
        add(titulo);

        registrarButton = new JButton("Registrar Reporte");
        registrarButton.setBounds(130, 80, 240, 40);
        registrarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        registrarButton.setBackground(new Color(51, 153, 255));
        registrarButton.setForeground(Color.WHITE);
        registrarButton.setFocusPainted(false);
        registrarButton.setIcon(cargarIcono("add.png"));
        add(registrarButton);

        verButton = new JButton("Ver Reportes");
        verButton.setBounds(130, 140, 240, 40);
        verButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        verButton.setBackground(new Color(51, 153, 255));
        verButton.setForeground(Color.WHITE);
        verButton.setFocusPainted(false);
        verButton.setIcon(cargarIcono("list.png"));
        add(verButton);

        estadisticasButton = new JButton("Ver Estadísticas");
        estadisticasButton.setBounds(130, 200, 240, 40);
        estadisticasButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        estadisticasButton.setBackground(new Color(51, 153, 255));
        estadisticasButton.setForeground(Color.WHITE);
        estadisticasButton.setFocusPainted(false);
        estadisticasButton.setIcon(cargarIcono("chart.png"));
        add(estadisticasButton);

        salirButton = new JButton("Salir");
        salirButton.setBounds(130, 260, 240, 40);
        salirButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        salirButton.setBackground(new Color(255, 102, 102));
        salirButton.setForeground(Color.WHITE);
        salirButton.setFocusPainted(false);
        salirButton.setIcon(cargarIcono("exit.png"));
        add(salirButton);

        registrarButton.addActionListener((ActionEvent e) -> {
            new RegistrarReporteForm();
        });

        verButton.addActionListener((ActionEvent e) -> {
            new VerReportesForm();
        });

        estadisticasButton.addActionListener((ActionEvent e) -> {
            new EstadisticasForm();
        });

        salirButton.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        setVisible(true);
    }

    private ImageIcon cargarIcono(String nombreArchivo) {
        URL url = getClass().getResource("/com/saludpredictiva/resources/img/" + nombreArchivo);
        if (url != null) {
            Image img = new ImageIcon(url).getImage();
            Image redimensionada = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            return new ImageIcon(redimensionada);
        } else {
            System.err.println("No se encontró el ícono: " + nombreArchivo);
            return null;
        }
    }
}
