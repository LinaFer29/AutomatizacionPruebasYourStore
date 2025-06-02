package com.opencart.test;

import com.opencart.pages.HomePage;
import com.opencart.pages.SuccessMessagePage;
import com.opencart.pages.UserRegistrationPage;
import com.opencart.utils.Constants;
import com.opencart.utils.Excel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserRegistrationTest extends BaseTest {

    @Test
    public void registerUser () {

        //Pages
        HomePage homePage = new HomePage(driver);
        UserRegistrationPage registrationPage = new UserRegistrationPage(driver);
        SuccessMessagePage successMessagePage = new SuccessMessagePage(driver);
        //Excels
        Excel excelUsers = new Excel(Constants.FILE_PATH_EXCEL_USERS);

        //Datos
        String firstName = "";
        String lastName = "";
        String email = "";
        String telephone = "";
        String password = "";
        String passConfirm = "";
        String subscribe = "";


        homePage.navigateTo(Constants.BASE_URL);

        List<String[]> users = excelUsers.readData();
        // Verificar que la lista no esté vacía
        if (!users.isEmpty()) {
            // Obtener el primer elemento (primera fila de datos)
            String[] firstRow = users.get(4);

            firstName = firstRow[0];
            lastName = firstRow[1];
            email = firstRow[2];
            telephone = firstRow[3];
            password = firstRow[4];
            passConfirm = firstRow[5];
            subscribe = firstRow[6];
            // Imprimir los datos del primer elemento

        } else {
            System.out.println("No se encontraron datos en el archivo.");
        }

        //Navegar al registro de usuario
        homePage.selectMenuOption("My Account");
        homePage.selectSubOption("Register");

        //Assert
        Assertions.assertEquals(registrationPage.getTitle(),"Register Account","No se navegó a la página de registro.");

        //Diligenciar formulario
        registrationPage.setInputValue(firstName,"firstname");
        registrationPage.setInputValue(lastName,"lastname");
        registrationPage.setInputValue(email,"email");
        registrationPage.setInputValue(telephone,"telephone");
        registrationPage.setInputValue(password,"password");
        registrationPage.setInputValue(passConfirm,"confirm");

        if (subscribe.equalsIgnoreCase("yes")){
            registrationPage.selectOption("newsletter",1);
        } else {
            registrationPage.selectOption("newsletter",0);
        }

        registrationPage.selectOption("agree",1);

        registrationPage.selectButton("Continue");

        //Verificar mensaje de exito
        String expectedMessage = "Congratulations! Your new account has been successfully created!";
        String actualMessage = successMessagePage.getSuccessMessage();

        Assertions.assertEquals(expectedMessage,actualMessage, "El mensaje de éxito no es el esperado.");

        homePage.selectMenuOption("My Account");
        homePage.selectSubOption("Logout");

    }
}
