IF OBJECT_ID('ReporteEpidemico', 'U') IS NOT NULL
    DROP TABLE ReporteEpidemico;

CREATE TABLE ReporteEpidemico (
    id INT IDENTITY(1,1) PRIMARY KEY,
    facultad VARCHAR(100) NOT NULL,
    turno VARCHAR(50) NOT NULL,
    ciclo INT NOT NULL,
    sintomas VARCHAR(MAX) NOT NULL,
    cantidadCasos INT NOT NULL,
    fecha DATE NOT NULL,
    nombrePaciente VARCHAR(100) NOT NULL,
    duracionSintomas INT NOT NULL,
    diagnosticoProbable VARCHAR(100) NOT NULL
);
