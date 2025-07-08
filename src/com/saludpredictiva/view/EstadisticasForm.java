package com.saludpredictiva.view;

import com.saludpredictiva.controller.ReporteEpidemicoController;
import com.saludpredictiva.model.ReporteEpidemico;
import com.saludpredictiva.util.ExportadorUtil;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class EstadisticasForm extends JFrame {
    private ReporteEpidemicoController controller;
    private JTable tablaReportes;
    private JButton btnExportarPDF;
    private JButton btnExportarExcel;

    public EstadisticasForm() {
        controller = new ReporteEpidemicoController();

        setTitle("Estadísticas de Brotes Epidemiológicos");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(204, 229, 255));

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.add("Casos por Facultad", crearPanelCasosPorFacultad());
        tabbedPane.add("Síntomas más Reportados", crearPanelSintomas());
        tabbedPane.add("Listado Completo", crearPanelListado());

        add(tabbedPane, BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel crearPanelCasosPorFacultad() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<String, Integer> datos = controller.obtenerCasosPorFacultad();
        for (String facultad : datos.keySet()) {
            dataset.addValue(datos.get(facultad), "Casos", facultad);
        }
        JFreeChart chart = ChartFactory.createBarChart("Casos por Facultad", "Facultad", "Casos", dataset);
        return new ChartPanel(chart);
    }

    private JPanel crearPanelSintomas() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<String, Integer> datos = controller.obtenerSintomasMasReportados();
        for (String sintoma : datos.keySet()) {
            dataset.addValue(datos.get(sintoma), "Síntomas", sintoma);
        }
        JFreeChart chart = ChartFactory.createBarChart("Síntomas más Reportados", "Síntoma", "Frecuencia", dataset);
        return new ChartPanel(chart);
    }



    private JPanel crearPanelListado() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(204, 229, 255));

        tablaReportes = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaReportes);
        panel.add(scrollPane, BorderLayout.CENTER);

        List<ReporteEpidemico> reportes = controller.obtenerTodosLosReportes();
        String[] columnas = {
                "ID", "Facultad", "Turno", "Ciclo", "Síntomas", "Casos", "Fecha", "Nombre Paciente", "Duración Síntomas", "Diagnóstico Probable"
        };

        String[][] datos = new String[reportes.size()][columnas.length];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < reportes.size(); i++) {
            ReporteEpidemico r = reportes.get(i);
            datos[i][0] = String.valueOf(r.getId());
            datos[i][1] = r.getFacultad();
            datos[i][2] = r.getTurno();
            datos[i][3] = String.valueOf(r.getCiclo());
            datos[i][4] = r.getSintomas();
            datos[i][5] = String.valueOf(r.getCantidadCasos());
            datos[i][6] = sdf.format(r.getFecha());
            datos[i][7] = r.getNombrePaciente();
            datos[i][8] = String.valueOf(r.getDuracionSintomas());
            datos[i][9] = r.getDiagnosticoProbable();
        }

        tablaReportes.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(204, 229, 255));

        btnExportarPDF = new JButton("Exportar PDF");
        btnExportarPDF.setBackground(new Color(51, 153, 255));
        btnExportarPDF.setForeground(Color.WHITE);
        btnExportarPDF.setFont(new Font("Segoe UI", Font.BOLD, 13));
        URL pdfIconUrl = getClass().getResource("/com/saludpredictiva/resources/img/pdf.png");
        if (pdfIconUrl != null) btnExportarPDF.setIcon(new ImageIcon(pdfIconUrl));

        btnExportarExcel = new JButton("Exportar Excel");
        btnExportarExcel.setBackground(new Color(51, 153, 255));
        btnExportarExcel.setForeground(Color.WHITE);
        btnExportarExcel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        URL excelIconUrl = getClass().getResource("/com/saludpredictiva/resources/img/excel.png");
        if (excelIconUrl != null) btnExportarExcel.setIcon(new ImageIcon(excelIconUrl));

        btnExportarPDF.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar como PDF");
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                ExportadorUtil.exportarTablaAPDF(tablaReportes, fileChooser.getSelectedFile().getAbsolutePath() + ".pdf");
            }
        });

        btnExportarExcel.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar como Excel");
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                ExportadorUtil.exportarTablaAExcel(tablaReportes, fileChooser.getSelectedFile().getAbsolutePath() + ".xlsx");
            }
        });

        panelBotones.add(btnExportarPDF);
        panelBotones.add(btnExportarExcel);
        panel.add(panelBotones, BorderLayout.SOUTH);

        return panel;
    }
}
