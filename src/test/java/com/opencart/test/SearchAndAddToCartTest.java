package com.opencart.test;

import com.opencart.pages.CategoryPage;
import com.opencart.pages.HomePage;
import com.opencart.utils.Constants;
import com.opencart.utils.Excel;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SearchAndAddToCartTest extends BaseTest{

    @Test
    public void SearchProductAndAddToCart(){

        HomePage basePage = new HomePage(driver);
        CategoryPage categorypage = new CategoryPage(driver);
        Excel excelProducts = new Excel(Constants.FILE_PATH_EXCEL_PRODUCTS);

        //Leer datos del Excel
        List<String[]> products = excelProducts.readData();

        //Abrir la pagina
        basePage.navigateTo(Constants.BASE_URL);

        //Test con los datos del Excel
        for (int i = 0; i < products.size(); i++) {
            String[] rowData = products.get(i);
            // Verificar que cada fila tenga al menos 3 columnas: categoria, subcategoria, producto
            if (rowData.length >= 3) {
                String category = rowData[0]; // Columna A: categoria
                String subCategory = rowData[1]; // Columna B: subcategoria
                String product = rowData[2]; // Columna C: producto

                //Prueba para verificar que si se esta obteniendo lo datos
                //System.out.println("Ejecutando test para: Categoría='" + category + "', Subcategoría='" + subCategory + "', Producto='" + product + "'");

                // Llamar al metodo selectProductsCategory con los datos del Excel
                categorypage.selectProductsCategory(category,subCategory,product);

            } else {
                System.out.println("Fila " + (i + 1) + " del Excel no tiene suficientes columnas (se esperaban 3). Saltando esta fila.");
            }
        }
    }
}
