package com.opencart.test;
import com.opencart.pages.*;
import com.opencart.utils.Constants;
import com.opencart.utils.Excel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;


public class EndToEndTest extends BaseTest{

    @Test
    public void testEndToEndFlow() {
        // Páginas
        HomePage homePage = new HomePage(driver);
        UserRegistrationPage registrationPage = new UserRegistrationPage(driver);
        UserLoginPage loginPage = new UserLoginPage(driver);
        SuccessMessagePage successMessagePage = new SuccessMessagePage(driver);
        Excel excelUsers = new Excel(Constants.FILE_PATH_EXCEL_USERS);
        Excel excelProducts = new Excel(Constants.FILE_PATH_EXCEL_PRODUCTS);
        Excel excelOutput = new Excel(Constants.OUTPUT_FILE_PATH_EXCEL);

        // Paso 1: Registro de usuario
        homePage.selectMenuOption("My Account");
        homePage.selectSubOption("Register");
        String[] dataUser = registrationPage.getDataUserFromExcel(excelUsers);

        //Assert
        Assertions.assertEquals(registrationPage.getTitle(),"Register Account","No se navegó a la página de registro.");

        registrationPage.fillForm(dataUser);

        String expectedMessage = "Congratulations! Your new account has been successfully created!";
        String actualMessage = successMessagePage.getSuccessMessage();

        Assertions.assertEquals(expectedMessage,actualMessage, "El mensaje de éxito no es el esperado.");

        homePage.selectMenuOption("My Account");
        homePage.selectSubOption("Logout");

        //Paso 2: Inicio de Sesión

        String[] validUser = loginPage.searchValidUser(excelUsers.readData());

        // Verificar que se encontró un usuario válido
        Assertions.assertNotNull(validUser, "No se encontró un usuario válido en el Excel");

        String email = validUser[2];     // Columna C: email
        String password = validUser[4]; // Columna E: password

        // Navegar al login
        homePage.selectMenuOption("My Account");
        homePage.selectSubOption("Login");

        // Assert - verificar navegación a página de login
        Assertions.assertEquals("Account Login", loginPage.getTitle(), "No se navegó a la página de login.");

        // Realizar login
        loginPage.login(email, password);

        // Verificar login exitoso
        Assertions.assertTrue(loginPage.isLoginSuccessful(), "El login no fue exitoso");

        // Paso 3: Búsqueda y agregado al carrito
        CategoryPage categoryPage = new CategoryPage(driver);
        categoryPage.addProductsToCart(excelProducts.readData());

        // Paso 4: Validación del carrito
        CartPage cartPage = new CartPage(driver);
        //cartPage.validateProductsFromExcel(excelProducts.readData());
        List<String[]> rawData = excelProducts.readData();
        // Extraer solo la tercer columna (producto)
        List<String> expectedProducts = rawData.stream()
                .map(row -> row[2])// Columna C: producto
                .toList();
        // Navegar a la pagina del carrito
        homePage.selectMenuOption("Shopping Cart");

        // Assert
        Assertions.assertEquals(cartPage.getTitle(),"Shopping Cart","No se navegó a la página de Carrito.");

        // Obtener los productos actualmente en el carrito
        List<String> cartProducts = cartPage.getProductNamesInCart();
        // Lista para almacenar los productos exitosos
        List<String[]> successfulProducts = new ArrayList<>();

        // Verificar que los productos esperados estén en el carrito
        for (String expected : expectedProducts) {
            Assert.assertTrue(cartProducts.contains(expected), "El producto '" + expected + "' no se encontró en el carrito.");
            successfulProducts.add(new String[]{expected});
        }

        // Escribir los productos exitosos en un nuevo archivo Excel
        excelOutput.writeData(successfulProducts);

        homePage.selectMenuOption("My Account");
        homePage.selectSubOption("Logout");

        // Verificar que el logout fue exitoso verificando el título de la página
        Assertions.assertEquals("Account Logout", homePage.getTitle(), "El logout no fue exitoso");
    }
}
