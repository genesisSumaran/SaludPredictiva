CREATE DATABASE SaludPredictiva;
GO

USE SaludPredictiva;
GO

CREATE TABLE Usuario (
    id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(100) NOT NULL,
    correo NVARCHAR(100) NOT NULL UNIQUE,
    contrasena NVARCHAR(100) NOT NULL,
    rol NVARCHAR(50) NOT NULL
);
GO

CREATE TABLE ReporteEpidemico (
    id INT IDENTITY(1,1) PRIMARY KEY,
    ubicacion NVARCHAR(150) NOT NULL,
    sintomas NVARCHAR(MAX) NOT NULL,
    cantidadCasos INT NOT NULL,
    fecha DATE NOT NULL
);
GO
