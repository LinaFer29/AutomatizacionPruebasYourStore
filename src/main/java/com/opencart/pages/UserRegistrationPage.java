package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

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




}
