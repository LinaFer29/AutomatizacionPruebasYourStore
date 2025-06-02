package com.opencart.pages;

import com.opencart.utils.Constants;
import com.opencart.utils.Excel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class UserRegistrationPage extends BasePage {

    public UserRegistrationPage (WebDriver driver) { super(driver);}

    private By formInput (String inputName) {
        return By.xpath("//fieldset//input[@name=\""+ inputName + "\"]");
    }

    private By optionInput (String inputName, int value){
        return By.xpath("//input[@name=\""+ inputName +"\" and @value=\""+ String.valueOf(value) +"\"]");
    }

    private By submitInput (String value){
        return By.xpath("//div[@class=\"buttons\"]//input[@value=\""+ value +"\"]");
    }

    public void setInputValue (String value, String inputName){
        WebElement input = driver.findElement(formInput(inputName));
        input.clear();
        input.sendKeys(value);
    }

    public void selectOption (String inputName, int value){
        driver.findElement(optionInput(inputName,value)).click();
    }

    public void selectButton (String value){
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(submitInput(value)));
        button.click();
    }

    public String[] getDataUserFromExcel(Excel excelUsers){
        String[] dataUser = new String[0];
        List<String[]> users = excelUsers.readData();

        // Verificar que la lista no esté vacía
        if (!users.isEmpty()) {
            // Obtener el elemento (fila # de datos)
            dataUser = users.get(Constants.INDEX_USER);
        } else {
            System.out.println("No se encontraron datos en el archivo.");
        }
        return dataUser;
    }

    public void fillForm(String[] data){
        setInputValue(data[0],"firstname");
        setInputValue(data[1],"lastname");
        setInputValue(data[2],"email");
        setInputValue(data[3],"telephone");
        setInputValue(data[4],"password");
        setInputValue(data[5],"confirm");

        if (data[6].equalsIgnoreCase("yes")){
            selectOption("newsletter",1);
        } else {
            selectOption("newsletter",0);
        }

        selectOption("agree",1);
        selectButton("Continue");
    }

}
