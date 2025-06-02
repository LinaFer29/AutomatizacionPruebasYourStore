package com.opencart.test;

import com.opencart.pages.CategoryPage;
import com.opencart.pages.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.opencart.pages.CartPage;
import com.opencart.utils.Constants;
import com.opencart.utils.Excel;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class CartValidationTest extends BaseTest {

    @Test
    public void validateProductsFromExcelInCart(){

        HomePage page = new HomePage(driver);
        CartPage cart = new CartPage(driver);
        CategoryPage categoryPage = new CategoryPage(driver);

        // Leer productos del Excel
        Excel excelOutput = new Excel(Constants.OUTPUT_FILE_PATH_EXCEL);
        Excel excel = new Excel(Constants.FILE_PATH_EXCEL_PRODUCTS);
        List<String[]> rawData = excel.readData();

        // Extraer solo la tercer columna (producto)
        List<String> expectedProducts = rawData.stream()
                .map(row -> row[2])// Columna C: producto
                .toList();


        // Abrir la página principal
        page.navigateTo(Constants.BASE_URL);

        // Agregar productos al carrito
        for (String[] rowData : rawData) {
            if (rowData.length >= 3) {
                String category = rowData[0]; // Columna A: categoría
                String subCategory = rowData[1]; // Columna B: subcategoría
                String product = rowData[2]; // Columna C: producto

                // Agregar el producto al carrito
                categoryPage.selectProductsCategory(category, subCategory, product);
            } else {
                System.out.println("Fila con datos insuficientes. Saltando...");
            }
        }

        // Navegar a la pagina del carrito
        page.selectMenuOption("Shopping Cart");

        // Assert
        Assertions.assertEquals(cart.getTitle(),"Shopping Cart","No se navegó a la página de Carrito.");

        // Obtener los productos actualmente en el carrito
        List<String> cartProducts = cart.getProductNamesInCart();
        // Lista para almacenar los productos exitosos
        List<String[]> successfulProducts = new ArrayList<>();

        // Verificar que los productos esperados estén en el carrito
        for (String expected : expectedProducts) {
            Assert.assertTrue(cartProducts.contains(expected), "El producto '" + expected + "' no se encontró en el carrito.");
            successfulProducts.add(new String[]{expected});
        }

        // Escribir los productos exitosos en un nuevo archivo Excel
        excelOutput.writeData(successfulProducts);
    }
}


