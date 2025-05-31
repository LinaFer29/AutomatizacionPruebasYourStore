package com.opencart.test;

import com.opencart.pages.CategoryPage;
import com.opencart.pages.HomePage;
import com.opencart.pages.ProductDetailsPage;
import com.opencart.utils.Constants;
import com.opencart.utils.Excel;
import com.opencart.utils.Verify;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SelectProductTest extends BaseTest{

    @Test
    public void selectProduct() throws InterruptedException {
        //Pages
        HomePage homePage = new HomePage(driver);
        CategoryPage categoryPage = new CategoryPage(driver);

        //Excels
        Excel excel = new Excel(Constants.FILE_PATH_EXCEL);
        Excel resultsExcel = new Excel(Constants.OUTPUT_FILE_PATH_EXCEL);

        //Paso 1: Abrir pagina web, Navegar
        homePage.navigateTo(Constants.BASE_URL);

        //Muestra de Lectura de datos del Excel
        // Formato de Data: [{"product name", "model", "quantity", "unit price", "total"}, {...}, {...}]
        List<String[]> data = excel.readData();
        data.forEach(dataProd -> {
                    String category = dataProd[0];
                    String subCategory = dataProd[1];
                    String product = dataProd[2];
                    System.out.println("Categoria:" + category + ", Subcategoria: " + subCategory + ", Product: " + product);
        });

        //Muestra de Escritura de datos en el Excel Output
        // Formato de Data: [{"product name", "model", "quantity", "unit price", "total"}, {...}, {...}]
        List<String[]> newData = new ArrayList<>();
        newData.add(new String[]{"Iphone", "product 11", "1","123,20", "123,20"});
        resultsExcel.writeData(newData);


        //Paso 2: Seleccionar categoria y subcategoria
        // Acciones
        homePage.selectCategory("Desktops");
        homePage.selectSubCategory("Mac");

        //Assert
        Assertions.assertEquals(homePage.getTitle(),"Mac");

        //Verify
        //Verify.verify(() -> assertThat(categoryPage.getTitle()).contains("AA"));
        //Verify.verify(() -> assertThat(categoryPage.getTitle()).contains("CC"));
        //Verify.verify(() -> assertThat(categoryPage.getTitle()).contains("i"));
        //Verify.verify(() -> assertThat(categoryPage.getTitle()).contains("iMac"));

        //Verificación si se presento un error de los Verify
        //Verify.verifyAll();


        //Espera absoluta (No utilizar)
        //Thread.sleep(10000);
        /* Esperas de Selenium
            Espera Implicita, tiempo de espera para todos los elementos
            Espera Explicita, espera hasta que se cumpla la condicion (visible, Clicleable, etc)
            Fluent wait (Explicita Anvanzada), Tiempo de espera, frecuencia de revision, ignorar ciertas excepciones temporales
         */

        //Paso 3: Instanciar la CategoryPage y seleccionar producto
        categoryPage.selectProductByName("iMac");

        //Paso 4: Ingresar cantidad del producto
        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        productDetailsPage.setQuantity(3,"input-quantity");
        productDetailsPage.selectAddToCartBtn("button-cart");

    }
}
