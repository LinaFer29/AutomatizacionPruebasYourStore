package com.opencart.test;

import com.opencart.pages.HomePage;
import com.opencart.pages.UserRegistrationPage;
import com.opencart.utils.Constants;
import com.opencart.utils.Excel;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserRegistrationTest extends BaseTest {

    @Test
    public void registerUser () {

        //Pages
        HomePage homePage = new HomePage(driver);
        UserRegistrationPage registrationPage = new UserRegistrationPage(driver);
        //Excels
        Excel excelUsers = new Excel(Constants.FILE_PATH_EXCEL_USERS);

        List<String[]> users = excelUsers.readData();
        // Verificar que la lista no esté vacía
        if (!users.isEmpty()) {
            // Obtener el primer elemento (primera fila de datos)
            String[] firstRow = users.get(0);

            // Imprimir los datos del primer elemento
            System.out.println("Datos del primer elemento:");
            for (String value : firstRow) {
                System.out.println(value);
            }
        } else {
            System.out.println("No se encontraron datos en el archivo.");
        }



    }
}
