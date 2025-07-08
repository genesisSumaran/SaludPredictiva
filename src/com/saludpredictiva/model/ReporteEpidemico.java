package com.saludpredictiva.model;

import java.sql.Date;

public class ReporteEpidemico {
    private int id;
    private String facultad;
    private String turno;
    private int ciclo;
    private String sintomas;
    private int cantidadCasos;
    private Date fecha;
    private String nombrePaciente;
    private int duracionSintomas;
    private String diagnosticoProbable;

    public ReporteEpidemico(int id, String facultad, String turno, int ciclo, String sintomas, int cantidadCasos, Date fecha, String nombrePaciente, int duracionSintomas, String diagnosticoProbable) {
        this.id = id;
        this.facultad = facultad;
        this.turno = turno;
        this.ciclo = ciclo;
        this.sintomas = sintomas;
        this.cantidadCasos = cantidadCasos;
        this.fecha = fecha;
        this.nombrePaciente = nombrePaciente;
        this.duracionSintomas = duracionSintomas;
        this.diagnosticoProbable = diagnosticoProbable;
    }

    public int getId() {
        return id;
    }

    public String getFacultad() {
        return facultad;
    }

    public String getTurno() {
        return turno;
    }

    public int getCiclo() {
        return ciclo;
    }

    public String getSintomas() {
        return sintomas;
    }

    public int getCantidadCasos() {
        return cantidadCasos;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public int getDuracionSintomas() {
        return duracionSintomas;
    }

    public String getDiagnosticoProbable() {
        return diagnosticoProbable;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public void setCantidadCasos(int cantidadCasos) {
        this.cantidadCasos = cantidadCasos;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public void setDuracionSintomas(int duracionSintomas) {
        this.duracionSintomas = duracionSintomas;
    }

    public void setDiagnosticoProbable(String diagnosticoProbable) {
        this.diagnosticoProbable = diagnosticoProbable;
    }

    public String getaFacultad() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
