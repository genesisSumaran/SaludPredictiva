package com.saludpredictiva.controller;

import com.saludpredictiva.dao.UsuarioDAO;

public class UsuarioController {
    private UsuarioDAO dao;

    public UsuarioController() {
        dao = new UsuarioDAO();
    }

    public boolean validarUsuario(String correo, String contrasena) {
        return dao.validarCredenciales(correo, contrasena);
    }
}
