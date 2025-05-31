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
    private  String filePath;

    public Excel(String filePath) {
        this.filePath = filePath;
    }

    public List<String[]> readData() {
        List<String[]> data = new ArrayList<>(); // Formato de Data: [{"category", "subcategory", "product"}, {...}, {...}]
        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();

            // Comienza desde la segunda fila (índice 1)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                String category = formatter.formatCellValue(sheet.getRow(i).getCell(0));
                String Subcategory = formatter.formatCellValue(sheet.getRow(i).getCell(1));
                String product = formatter.formatCellValue(sheet.getRow(i).getCell(2));
                data.add(new String[]{category, Subcategory, product});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void writeData (List<String[]> data){ // Formato de Data: [{"product name", "model", "quantity", "unit price", "total"}, {...}, {...}]
        XSSFWorkbook workbook;
        XSSFSheet sheet;

        // Verificar si el archivo ya existe
        File file = new File(filePath);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheetAt(0); // Escribir en la primera hoja
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        } else {
            // Crear un nuevo archivo si no existe
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Sheet1");
        }

        // Escribir los datos en el archivo Excel
        // Formato de Data: [{"product name", "model", "quantity", "unit price", "total"}, {...}, {...}]
        int rowNum = sheet.getLastRowNum() + 1; // Comenzar después de la última fila existente
        for (String[] rowData : data) {
            Row row = sheet.createRow(rowNum++);
            for (int i = 0; i < rowData.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(rowData[i]);
            }
        }

        // Guardar los cambios en el archivo
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
}
