package com.saludpredictiva.view;

import com.saludpredictiva.controller.ReporteEpidemicoController;
import com.saludpredictiva.model.ReporteEpidemico;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;
import java.util.List;

public class VerReportesForm extends JFrame {
    private JTable tablaReportes;
    private JScrollPane scrollPane;
    private ReporteEpidemicoController controller;

    public VerReportesForm() {
        controller = new ReporteEpidemicoController();
        setTitle("Listado de Reportes Epidemiológicos");
        setSize(1100, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(204, 229, 255));
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel(" Reportes Epidemiológicos", JLabel.LEFT);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(new Color(0, 51, 102));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ImageIcon icon = cargarIcono("report.png");
        if (icon != null) {
            titulo.setIcon(icon);
        }

        add(titulo, BorderLayout.NORTH);

        tablaReportes = new JTable();
        tablaReportes.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaReportes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablaReportes.setRowHeight(28);
        tablaReportes.setGridColor(Color.LIGHT_GRAY);
        tablaReportes.setShowHorizontalLines(true);
        tablaReportes.setShowVerticalLines(false);
        tablaReportes.setBackground(Color.WHITE);
        tablaReportes.setSelectionBackground(new Color(179, 229, 252));
        tablaReportes.setSelectionForeground(Color.BLACK);
        ((DefaultTableCellRenderer) tablaReportes.getDefaultRenderer(Object.class)).setHorizontalAlignment(SwingConstants.CENTER);

        scrollPane = new JScrollPane(tablaReportes);
        add(scrollPane, BorderLayout.CENTER);

        cargarDatosEnTabla();
        setVisible(true);
    }

    private void cargarDatosEnTabla() {
        List<ReporteEpidemico> reportes = controller.obtenerTodosLosReportes();

        String[] columnas = {
            "ID", "Facultad", "Turno", "Ciclo", "Síntomas", "Casos", "Fecha",
            "Nombre Paciente", "Duración (días)", "Diagnóstico"
        };

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (ReporteEpidemico r : reportes) {
            Object[] fila = {
                r.getId(),
                r.getFacultad(),
                r.getTurno(),
                r.getCiclo(),
                r.getSintomas(),
                r.getCantidadCasos(),
                r.getFecha(),
                r.getNombrePaciente(),
                r.getDuracionSintomas(),
                r.getDiagnosticoProbable()
            };
            modelo.addRow(fila);
        }

        tablaReportes.setModel(modelo);
    }

    private ImageIcon cargarIcono(String nombreArchivo) {
        URL url = getClass().getResource("/com/saludpredictiva/resources/img/" + nombreArchivo);
        if (url != null) {
            Image img = new ImageIcon(url).getImage();
            Image redimensionada = img.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            return new ImageIcon(redimensionada);
        } else {
            System.err.println("No se encontró el ícono: " + nombreArchivo);
            return null;
        }
    }
}
