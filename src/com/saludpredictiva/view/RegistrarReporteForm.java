package com.saludpredictiva.view;

import com.saludpredictiva.controller.ReporteEpidemicoController;
import com.saludpredictiva.model.ReporteEpidemico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class RegistrarReporteForm extends JFrame {
    private JComboBox<String> cbFacultad;
    private JComboBox<String> cbTurno;
    private JComboBox<Integer> cbCiclo;
    private JTextField tfNombrePaciente;
    private JTextField tfDuracionSintomas;
    private JCheckBox cbFiebre, cbTos, cbDificultadRespirar, cbFatiga, cbDolorCabeza;
    private JButton btnGuardar;
    private ReporteEpidemicoController controller;

    public RegistrarReporteForm() {
        controller = new ReporteEpidemicoController();
        setTitle("Registrar Reporte Epidemiológico");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(204, 229, 255));
        setLayout(null);

        Font fuenteLabel = new Font("Segoe UI", Font.PLAIN, 14);
        Font fuenteCampo = new Font("Segoe UI", Font.BOLD, 14);

        JLabel lblFacultad = new JLabel("Facultad:");
        lblFacultad.setBounds(50, 30, 120, 25);
        lblFacultad.setFont(fuenteLabel);
        add(lblFacultad);

        cbFacultad = new JComboBox<>(new String[]{
            "Ingeniería", "Ciencias de la Salud", "Ciencias Sociales", "Educación", "Derecho"
        });
        cbFacultad.setBounds(180, 30, 300, 25);
        cbFacultad.setFont(fuenteCampo);
        add(cbFacultad);

        JLabel lblTurno = new JLabel("Turno:");
        lblTurno.setBounds(50, 70, 120, 25);
        lblTurno.setFont(fuenteLabel);
        add(lblTurno);

        cbTurno = new JComboBox<>(new String[]{"Mañana", "Tarde", "Noche"});
        cbTurno.setBounds(180, 70, 300, 25);
        cbTurno.setFont(fuenteCampo);
        add(cbTurno);

        JLabel lblCiclo = new JLabel("Ciclo:");
        lblCiclo.setBounds(50, 110, 120, 25);
        lblCiclo.setFont(fuenteLabel);
        add(lblCiclo);

        cbCiclo = new JComboBox<>();
        for (int i = 1; i <= 12; i++) cbCiclo.addItem(i);
        cbCiclo.setBounds(180, 110, 300, 25);
        cbCiclo.setFont(fuenteCampo);
        add(cbCiclo);

        JLabel lblNombre = new JLabel("Nombre del Paciente:");
        lblNombre.setBounds(50, 150, 160, 25);
        lblNombre.setFont(fuenteLabel);
        add(lblNombre);

        tfNombrePaciente = new JTextField();
        tfNombrePaciente.setBounds(220, 150, 260, 25);
        tfNombrePaciente.setFont(fuenteCampo);
        add(tfNombrePaciente);

        JLabel lblDuracion = new JLabel("Duración (días):");
        lblDuracion.setBounds(50, 190, 150, 25);
        lblDuracion.setFont(fuenteLabel);
        add(lblDuracion);

        tfDuracionSintomas = new JTextField();
        tfDuracionSintomas.setBounds(180, 190, 300, 25);
        tfDuracionSintomas.setFont(fuenteCampo);
        add(tfDuracionSintomas);

        JLabel lblSintomas = new JLabel("Síntomas:");
        lblSintomas.setBounds(50, 230, 150, 25);
        lblSintomas.setFont(fuenteLabel);
        add(lblSintomas);

        cbFiebre = new JCheckBox("Fiebre");
        cbTos = new JCheckBox("Tos");
        cbDificultadRespirar = new JCheckBox("Dificultad para respirar");
        cbFatiga = new JCheckBox("Fatiga");
        cbDolorCabeza = new JCheckBox("Dolor de cabeza");

        JCheckBox[] sintomas = {cbFiebre, cbTos, cbDificultadRespirar, cbFatiga, cbDolorCabeza};
        int y = 260;
        for (JCheckBox cb : sintomas) {
            cb.setBounds(70, y, 400, 25);
            cb.setBackground(new Color(204, 229, 255));
            cb.setFont(fuenteCampo);
            add(cb);
            y += 30;
        }

        btnGuardar = new JButton("Guardar Reporte");
        btnGuardar.setBounds(200, 400, 200, 40);
        btnGuardar.setBackground(new Color(0, 153, 204));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setIcon(cargarIcono("save.png"));
        add(btnGuardar);

        btnGuardar.addActionListener(this::guardarReporte);

        setVisible(true);
    }

    private void guardarReporte(ActionEvent e) {
        String facultad = (String) cbFacultad.getSelectedItem();
        String turno = (String) cbTurno.getSelectedItem();
        int ciclo = (int) cbCiclo.getSelectedItem();
        String nombre = tfNombrePaciente.getText().trim();
        String duracionStr = tfDuracionSintomas.getText().trim();

        if (nombre.isEmpty() || duracionStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre y duración de síntomas son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int duracion;
        try {
            duracion = Integer.parseInt(duracionStr);
            if (duracion <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Duración debe ser un número positivo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<String> sintomas = new ArrayList<>();
        if (cbFiebre.isSelected()) sintomas.add("Fiebre");
        if (cbTos.isSelected()) sintomas.add("Tos");
        if (cbDificultadRespirar.isSelected()) sintomas.add("Dificultad para respirar");
        if (cbFatiga.isSelected()) sintomas.add("Fatiga");
        if (cbDolorCabeza.isSelected()) sintomas.add("Dolor de cabeza");

        if (sintomas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar al menos un síntoma.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sintomasTexto = String.join(", ", sintomas);
        String diagnostico = determinarDiagnosticoProbable(sintomas);

        ReporteEpidemico reporte = new ReporteEpidemico(
            0, facultad, turno, ciclo, sintomasTexto, 1,
            new Date(System.currentTimeMillis()),
            nombre, duracion, diagnostico
        );

        boolean exito = controller.registrarReporte(reporte);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Reporte registrado exitosamente.");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar el reporte.");
        }
    }

    private String determinarDiagnosticoProbable(List<String> sintomas) {
        if (sintomas.contains("Fiebre") && sintomas.contains("Tos") && sintomas.contains("Dificultad para respirar")) {
            return "COVID-19 probable";
        } else if (sintomas.contains("Fiebre") && sintomas.contains("Tos")) {
            return "Gripe probable";
        } else if (sintomas.contains("Fatiga") && sintomas.contains("Dolor de cabeza")) {
            return "Estrés probable";
        }
        return "Indeterminado";
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
