package com.saludpredictiva.view;

import com.saludpredictiva.controller.UsuarioController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

public class LoginForm extends JFrame {
    private JTextField correoField;
    private JPasswordField contrasenaField;
    private JButton loginButton;
    private UsuarioController controller;

    public LoginForm() {
        controller = new UsuarioController();

        setTitle("SaludPredictiva - Iniciar Sesión");
        setSize(400, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(204, 229, 255));

        JLabel titulo = new JLabel("Bienvenido a SaludPredictiva");
        titulo.setBounds(70, 20, 260, 30);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titulo.setForeground(new Color(0, 51, 102));
        add(titulo);

        JLabel correoLabel = new JLabel("Correo:");
        correoLabel.setBounds(50, 70, 100, 25);
        correoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        add(correoLabel);

        correoField = new JTextField();
        correoField.setBounds(150, 70, 180, 25);
        add(correoField);

        JLabel contrasenaLabel = new JLabel("Contraseña:");
        contrasenaLabel.setBounds(50, 110, 100, 25);
        contrasenaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        add(contrasenaLabel);

        contrasenaField = new JPasswordField();
        contrasenaField.setBounds(150, 110, 180, 25);
        add(contrasenaField);

        loginButton = new JButton("Ingresar");
        loginButton.setBounds(135, 170, 130, 35);
        loginButton.setBackground(new Color(51, 153, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setFocusPainted(false);

        URL url = getClass().getResource("/com/saludpredictiva/resources/img/login.png");
        if (url != null) {
            ImageIcon icon = new ImageIcon(url);
            Image scaledImage = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            loginButton.setIcon(new ImageIcon(scaledImage));
            loginButton.setHorizontalAlignment(SwingConstants.LEFT);
            loginButton.setIconTextGap(10);
        }

        add(loginButton);

        loginButton.addActionListener((ActionEvent e) -> {
            String correo = correoField.getText();
            String contrasena = new String(contrasenaField.getPassword());

            boolean acceso = controller.validarUsuario(correo, contrasena);

            if (acceso) {
                JOptionPane.showMessageDialog(this, "Bienvenido.");
                new DashboardForm();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas.");
            }
        });

        setVisible(true);
    }
}
