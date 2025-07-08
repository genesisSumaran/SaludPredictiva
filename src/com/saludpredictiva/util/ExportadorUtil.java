package com.saludpredictiva.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;

public class ExportadorUtil {

    public static void exportarTablaAExcel(JTable tabla, String rutaArchivo) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Reportes");

        TableModel model = tabla.getModel();
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < model.getColumnCount(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(model.getColumnName(i));
        }

        for (int i = 0; i < model.getRowCount(); i++) {
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < model.getColumnCount(); j++) {
                Cell cell = row.createCell(j);
                Object valor = model.getValueAt(i, j);
                cell.setCellValue(valor != null ? valor.toString() : "");
            }
        }

        try (FileOutputStream fileOut = new FileOutputStream(rutaArchivo)) {
            workbook.write(fileOut);
            Desktop.getDesktop().open(new File(rutaArchivo));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al exportar a Excel: " + e.getMessage());
        }
    }

    public static void exportarTablaAPDF(JTable tabla, String rutaArchivo) {
        try {
            Document documento = new Document();
            PdfWriter.getInstance(documento, new FileOutputStream(rutaArchivo));
            documento.open();

            PdfPTable pdfTabla = new PdfPTable(tabla.getColumnCount());
            for (int i = 0; i < tabla.getColumnCount(); i++) {
                pdfTabla.addCell(new PdfPCell(new Phrase(tabla.getColumnName(i))));
            }

            for (int i = 0; i < tabla.getRowCount(); i++) {
                for (int j = 0; j < tabla.getColumnCount(); j++) {
                    Object valor = tabla.getValueAt(i, j);
                    pdfTabla.addCell(new PdfPCell(new Phrase(valor != null ? valor.toString() : "")));
                }
            }

            documento.add(pdfTabla);
            documento.close();
            Desktop.getDesktop().open(new File(rutaArchivo));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al exportar a PDF: " + e.getMessage());
        }
    }
}
