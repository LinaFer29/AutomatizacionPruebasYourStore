package com.opencart.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Excel {
    private String filePath;

    public Excel(String filePath) {
        this.filePath = filePath;
    }

    // Formato de Data: [{...}, {...}, {...}]
    public List<String[]> readData() {
        List<String[]> data = new ArrayList<>(); // Lista para almacenar los datos
        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheetAt(0); // Leer la primera hoja
            DataFormatter formatter = new DataFormatter();

            // Iterar sobre las filas, comenzando desde la segunda fila (índice 1)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue; // Saltar filas vacías

                // Crear un arreglo dinámico para almacenar los valores de las columnas
                String[] rowData = new String[row.getLastCellNum()];

                // Iterar sobre las celdas de la fila
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    rowData[j] = formatter.formatCellValue(cell); // Obtener el valor de la celda como String
                }

                // Agregar la fila a la lista de datos
                data.add(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void writeData (List<String[]> data){ // Formato de Data: [{"product name", "model", "quantity", "unit price", "total"}, {...}, {...}]
        // Crear un nuevo libro de Excel
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("ProductsInCart");

        // Escribir los datos en el archivo Excel
        // Formato de Data: [{"product name", "model", "quantity", "unit price", "total"}, {...}, {...}]
        int rowNum = 0; // Comenzar desde la primera fila
        for (String[] rowData : data) {
            Row row = sheet.createRow(rowNum++);
            for (int i = 0; i < rowData.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(rowData[i]);
            }
        }

        // Guardar los cambios en el archivo (sobreescribir si ya existe)
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
            System.out.println("Datos escritos exitosamente en el archivo Excel.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String[] searchValidUser (List<String[]> users,String flag) {
        String[] validUser = null;
        for (String[] user : users) {
            // Asumiendo que la columna 7 (índice 7) indica si el usuario es válido para login
            if (user.length > 7 && user[7].equalsIgnoreCase(flag)) {
                validUser = user;
                break;
            }
        }
        return validUser;
    }
}
