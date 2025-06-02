package com.opencart.test;

import com.opencart.pages.HomePage;
import com.opencart.pages.SuccessMessagePage;
import com.opencart.pages.UserRegistrationPage;
import com.opencart.utils.Constants;
import com.opencart.utils.Excel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class UserRegistrationTest extends BaseTest {

//    public UserRegistrationTest(WebDriver driver) {
//        super(driver);
//    }

    @Test
    public void registerUser () {

        //Pages
        HomePage homePage = new HomePage(driver);
        UserRegistrationPage registrationPage = new UserRegistrationPage(driver);
        SuccessMessagePage successMessagePage = new SuccessMessagePage(driver);
        //Excels
        Excel excelUsers = new Excel(Constants.FILE_PATH_EXCEL_USERS);

        //Navegar al registro de usuario
        homePage.selectMenuOption("My Account");
        homePage.selectSubOption("Register");

        String[] dataUser = registrationPage.getDataUserFromExcel(excelUsers);

        //Assert
        Assertions.assertEquals(registrationPage.getTitle(),"Register Account","No se navegó a la página de registro.");

        //Diligenciar formulario
        registrationPage.fillForm(dataUser);


        //Verificar mensaje de exito
        String expectedMessage = "Congratulations! Your new account has been successfully created!";
        String actualMessage = successMessagePage.getSuccessMessage();

        Assertions.assertEquals(expectedMessage,actualMessage, "El mensaje de éxito no es el esperado.");

        homePage.selectMenuOption("My Account");
        homePage.selectSubOption("Logout");

    }
}
