package com.opencart.test;

import com.opencart.pages.HomePage;
import org.junit.jupiter.api.Test;
import com.opencart.pages.CartPage;
import com.opencart.utils.Constants;
import com.opencart.utils.Excel;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.List;

public class CartValidationTest extends BaseTest {

    @Test
    public void validateProductsFromExcelInCart(){

        HomePage page = new HomePage(driver);
        CartPage cart = new CartPage(driver);

        // Read products from Excel
        Excel excel = new Excel(Constants.FILE_PATH_EXCEL_PRODUCTS);
        List<String[]> rawData = excel.readData();

        // verify that the Excel is not empty
        if (!rawData.isEmpty()) {
            // Obtener el primer elemento (primera fila de datos)
            String[] firstRow = rawData.getFirst();

            // Imprimir los datos del primer elemento
            System.out.println("Datos del primer elemento:");
            for (String value : firstRow) {
                System.out.println(value);
            }
        } else {
            System.out.println("No se encontraron datos en el archivo.");
        }

        // Extract only the third column (producto)
        List<String> expectedProducts = rawData.stream()
                .map(row -> row[2])
                .toList();

        /* Verify that the elements of third row is correct
        for(String value : expectedProducts){
            System.out.println(value);
        }
        */

        // Open url
        page.navigateTo(Constants.BASE_URL);

        // Navigate to cart page
        driver.findElement(By.cssSelector("#top-links a[title='Shopping Cart']")).click();

        // Get products currently in the cart
        List<String> cartProducts = cart.getProductNamesInCart();

        // Assert each expected product is present in the cart
        for (String expected : expectedProducts) {
            Assert.assertTrue(cartProducts.contains(expected),
                    "Product '" + expected + "' is not found in the cart.");
        }
    }
}


