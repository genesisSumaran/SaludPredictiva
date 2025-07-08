USE SaludPredictiva;
GO

CREATE PROCEDURE sp_ObtenerReportesPorUbicacion
    @ubicacion NVARCHAR(150)
AS
BEGIN
    SELECT * FROM ReporteEpidemico
    WHERE ubicacion = @ubicacion
    ORDER BY fecha DESC;
END;
GO

CREATE PROCEDURE sp_InsertarReporte
    @ubicacion NVARCHAR(150),
    @sintomas NVARCHAR(MAX),
    @cantidadCasos INT,
    @fecha DATE
AS
BEGIN
    INSERT INTO ReporteEpidemico (ubicacion, sintomas, cantidadCasos, fecha)
    VALUES (@ubicacion, @sintomas, @cantidadCasos, @fecha);
END;
GO
