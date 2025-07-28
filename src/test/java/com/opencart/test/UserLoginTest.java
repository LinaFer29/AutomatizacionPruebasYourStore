package com.opencart.test;

import com.opencart.pages.HomePage;
import com.opencart.pages.UserLoginPage;
import com.opencart.utils.Constants;
import com.opencart.utils.Excel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserLoginTest extends BaseTest {

    @Test
    public void loginUser() {
        // Pages
        HomePage homePage = new HomePage(driver);
        UserLoginPage loginPage = new UserLoginPage(driver);

        // Excel
        Excel excelUsers = new Excel(Constants.FILE_PATH_EXCEL_USERS);

        // Navegar a la página principal
        homePage.navigateTo(Constants.BASE_URL);

        // Leer datos del Excel
        List<String[]> users = excelUsers.readData();

        // Buscar un usuario válido (que tenga "true" en la columna correspondiente)
        String[] validUser = excelUsers.searchValidUser(users,"true");

        // Verificar que se encontró un usuario válido
        Assertions.assertNotNull(validUser, "No se encontró un usuario válido en el Excel");

        String email = validUser[2];     // Columna C: email
        String password = validUser[4];  // Columna E: password

        // Navegar al login
        homePage.selectMenuOption("My Account");
        homePage.selectSubOption("Login");

        // Assert - verificar navegación a página de login
        Assertions.assertEquals("Account Login", loginPage.getTitle(), "No se navegó a la página de login.");

        // Realizar login
        loginPage.login(email, password);

        // Verificar login exitoso
        Assertions.assertTrue(loginPage.isLoginSuccessful(), "El login no fue exitoso");

        // Hacer logout después del login exitoso (siguiendo el patrón del test de registro)
        homePage.selectMenuOption("My Account");
        homePage.selectSubOption("Logout");

        // Verificar que el logout fue exitoso verificando el título de la página
        Assertions.assertEquals("Account Logout", homePage.getTitle(), "El logout no fue exitoso");
    }

    @Test
    public void loginWithInvalidCredentials() {
        // Pages
        HomePage homePage = new HomePage(driver);
        UserLoginPage loginPage = new UserLoginPage(driver);

        // Navegar a la página principal
        homePage.navigateTo(Constants.BASE_URL);

        // Navegar al login
        homePage.selectMenuOption("My Account");
        homePage.selectSubOption("Login");

        // Assert - verificar navegación a página de login
        Assertions.assertEquals("Account Login", loginPage.getTitle(), "No se navegó a la página de login.");

        // Intentar login con credenciales inválidas
        loginPage.login("invalid@email.com", "wrongpassword");

        // Verificar que aparece mensaje de error
        String errorMessage = loginPage.getErrorMessage();
        Assertions.assertTrue(errorMessage.contains("Warning"), "No se mostró mensaje de error para credenciales inválidas");
    }
}
