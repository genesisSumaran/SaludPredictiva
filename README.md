# SaludPredictiva

SaludPredictiva es una aplicación de escritorio desarrollada en Java Swing bajo una arquitectura de 4 capas (Modelo - DAO - Controlador - Vista). Está orientada a la detección y prevención de brotes epidémicos en entornos universitarios, permitiendo registrar síntomas, generar reportes, visualizar estadísticas y exportar datos.

---

## Funcionalidades principales

- Registro de reportes con datos como:
  - Facultad
  - Turno
  - Ciclo
  - Síntomas
  - Duración
  - Diagnóstico
- Visualización completa de reportes registrados
- Estadísticas gráficas generadas con JFreeChart:
  - Casos por facultad
  - Síntomas más frecuentes
  - Tendencia diaria de reportes
- Exportación de reportes a:
  - Excel (.xlsx)
  - PDF (.pdf)
- Módulo de autenticación con roles de usuario:
  - Administrador
  - Supervisor
  - Lector

---

## Estructura del proyecto

SaludPredictiva/
├── src/
│   └── com/saludpredictiva/
│       ├── model/           ← Clases de datos (Modelo)
│       ├── dao/             ← Acceso a base de datos (DAO)
│       ├── controller/      ← Lógica del negocio
│       ├── view/            ← Interfaces gráficas (Swing)
│       └── resources/img/   ← Íconos PNG de botones
├── BD/
│   ├── script.sql           ← Script de estructura y datos
│   └── respaldo.bak         ← Respaldo de la BD (SQL Server)
├── lib/                     ← Librerías externas (POI, JFreeChart, JDBC)
├── dist/                    ← Ejecutables y Javadoc generado
└── README.md

---


## Usuarios:

SQL: 

admin
admin

Login:

admin@salud.com
admin123

---

## Requisitos del sistema

- Java SE 8 o superior  
- NetBeans IDE 12 o superior  
- SQL Server 2019 o superior  
- Librerías externas necesarias:
  - poi-5.x.x.jar
  - poi-ooxml-5.x.x.jar
  - commons-collections4-x.x.jar
  - xmlbeans-x.x.jar
  - ooxml-schemas-x.x.jar
  - jfreechart-x.x.x.jar
  - jcommon-x.x.x.jar
  - mssql-jdbc-x.x.x.jre8.jar

---

## Instrucciones de uso

1. Clona el repositorio:
   git clone https://github.com/genesisSumaran/SaludPredictiva.git

2. Abre el proyecto en NetBeans IDE  
3. Configura la conexión a la base de datos SQL Server  
4. Agrega las librerías externas desde la carpeta lib/  
5. Ejecuta el proyecto desde view/Main.java



